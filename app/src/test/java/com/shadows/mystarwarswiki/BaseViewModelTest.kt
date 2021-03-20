package com.shadows.mystarwarswiki

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shadows.mystarwarswiki.dispatcher.CoroutineDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
open class BaseViewModelTest {

    @get:Rule
     val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineDispatcherRule = CoroutineDispatcherRule()

}