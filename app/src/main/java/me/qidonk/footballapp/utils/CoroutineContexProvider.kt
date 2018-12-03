package me.qidonk.footballapp.utils

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class CoroutineContexProvider {
    open val main: CoroutineContext by lazy { Dispatchers.Main }
}