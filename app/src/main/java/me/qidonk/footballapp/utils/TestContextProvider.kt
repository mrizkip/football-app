package me.qidonk.footballapp.utils

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestContextProvider : CoroutineContexProvider() {
    override val main: CoroutineContext = Dispatchers.Unconfined
}