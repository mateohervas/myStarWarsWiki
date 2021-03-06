package com.shadows.mystarwarswiki.dispatcher

import com.shadows.mystarwarswiki.dummydata.*
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class DummyDataDispatcher: Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when(request.path){
            "/people/?search=$SEARCH_FOR_LUKE" ->{
                    MockResponse()
                        .setResponseCode(200)
                        .setBody(luke)
                }
            "/people/?search=$SEARCH_FOR_EMPTY" ->{
                MockResponse()
                    .setResponseCode(200)
                    .setBody(no_results)
            }
            SEARCH_FOR_PLANET ->{
                MockResponse()
                    .setResponseCode(200)
                    .setBody(planet)
            }

            SEARCH_FOR_SPECIES -> {
                MockResponse()
                    .setResponseCode(200)
                    .setBody(species)
            }
            SEARCH_FOR_FILMS ->{
                MockResponse()
                    .setResponseCode(200)
                    .setBody(film)
            }

            else -> throw IllegalArgumentException("We could not find the path: ${request.path}")
        }
    }

    companion object{
        const val SEARCH_FOR_LUKE = "luke"
        const val SEARCH_FOR_EMPTY = "mateo"
        const val SEARCH_FOR_PLANET = "/planets/1/"
        const val SEARCH_FOR_SPECIES = "/species/1/"
        const val SEARCH_FOR_FILMS = "/films/1/"
    }
}