package com.example.newsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.newsapp.utils.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.mockito.MockitoAnnotations


abstract class BaseViewModelTest{

    @get:Rule
    open val instantExecutorRule = InstantTaskExecutorRule()


    @ExperimentalCoroutinesApi
    @get:Rule
    open val coroutineTestRule = CoroutineTestRule()

    open fun setup() {
        MockitoAnnotations.initMocks(this)
    }
}