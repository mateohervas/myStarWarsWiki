package com.shadows.mystarwarswiki

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.gson.GsonBuilder
import com.shadows.core.api.MyStarWarsApi
import com.shadows.mystarwarswiki.data.MyStarWarsDatabase
import com.shadows.mystarwarswiki.data.dao.CharacterDao
import com.shadows.mystarwarswiki.dispatcher.DummyDataDispatcher
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

//this base test class will let us mock an API Server and a Room database for our tests
internal open class BaseTest {

    // these lateinit vars will let us mock a web server
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    lateinit var starWarsAPI: MyStarWarsApi

    // database and dao
    private lateinit var database: MyStarWarsDatabase
    protected lateinit var characterDao: CharacterDao

    @Before
    open fun setup() {
        mockWebServer = MockWebServer()
        //This dispatcher will let us consume dummy data for our tests
        mockWebServer.dispatcher = DummyDataDispatcher()
        mockWebServer.start()

        val gson = GsonBuilder()
            .serializeNulls()
            .create()

        okHttpClient = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
        //mock star wars api creation
        starWarsAPI = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(MyStarWarsApi::class.java)

        //mock star wars room database creation
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, MyStarWarsDatabase::class.java).allowMainThreadQueries().build()
        characterDao = database.getCharacterDao()
    }

    @After
    @Throws(IOException::class)
    open fun clearMocks() {
        mockWebServer.shutdown()
        database.close()
    }
}