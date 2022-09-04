package com.hyun6ik.webfluxbasic.book

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.atomic.AtomicInteger

@Service
class BookService {

    private final val nextId = AtomicInteger(0)

    val books = mutableListOf(
        Book(id = nextId.incrementAndGet(), name = "코틀린 인 액션", price = 30000),
        Book(id = nextId.incrementAndGet(), name = "HTTP 완벽 가이드", price = 40000),
    )

    fun getAll() : Flux<Book> {
        return books.toFlux()
    }

    fun getBook(id: Int): Mono<Book> {
        return books.find { book -> book.id == id }.toMono()
    }

    fun create(request: Map<String, Any>): Mono<Book> {
        return Mono.just(request)
            .map { map ->
                val book = Book(
                    id = nextId.incrementAndGet(),
                    name = map["name"].toString(),
                    price = map["price"] as Int
                )
                books.add(book)
                book
            }
    }

    fun delete(id: Int): Mono<Void> {
        return Mono.justOrEmpty(books.find { book -> book.id == id })
            .map { book -> books.remove(book) }
            .then()
    }
}