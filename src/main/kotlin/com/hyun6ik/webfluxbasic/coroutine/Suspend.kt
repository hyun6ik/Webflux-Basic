package com.hyun6ik.webfluxbasic.coroutine

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() {
    doSomething()
}

fun printHello() = println("Hello")

suspend fun doSomething() = coroutineScope {

    launch {
        delay(200)
        println("World")
    }

    launch {
        printHello()
    }
}
