package me.qidonk.footballapp.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import me.qidonk.footballapp.utils.CoroutineContexProvider
import kotlin.coroutines.CoroutineContext

class TestContextProvider : CoroutineContexProvider() {
    @ExperimentalCoroutinesApi
    override val main: CoroutineContext = Dispatchers.Unconfined
}