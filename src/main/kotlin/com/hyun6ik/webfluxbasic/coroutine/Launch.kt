package com.hyun6ik.webfluxbasic.coroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {

    val job1: Job = launch {
        val elapsedTime = measureTimeMillis {
            delay(150)
        }
        println("Async task-1 $elapsedTime ms")
    }

    job1.cancel()

    val job2: Job = launch(start = CoroutineStart.LAZY) {
        val elapsedTime = measureTimeMillis {
            delay(100)
        }
        println("Async task-2 $elapsedTime ms")
    }

    job2.start()
    println("Hello")
}