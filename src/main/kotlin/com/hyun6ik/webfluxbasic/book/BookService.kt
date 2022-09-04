package com.hyun6ik.webfluxbasic.book

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.atomic.AtomicLong

@Service
class BookService(
    private val bookRepository: BookRepository,
) {

    private final val nextId = AtomicLong(0)

    val log = LoggerFactory.getLogger(javaClass)

    val books = mutableListOf(
        Book(id = nextId.incrementAndGet(), name = "코틀린 인 액션", price = 30000),
        Book(id = nextId.incrementAndGet(), name = "HTTP 완벽 가이드", price = 40000),
    )

    fun getAll() : Flux<Book> {
        return books.toFlux()
    }

    fun getBook(id: Long): Mono<Book> {
        return books.find { book -> book.id == id }.toMono()
    }

    @Transactional
    fun create(requestMap: Map<String, Any>): Mono<Book> {
//        return Mono.just(request)
//            .map { map ->
//                val book = Book(
//                    id = nextId.incrementAndGet(),
//                    name = map["name"].toString(),
//                    price = map["price"] as Int
//                )
//                books.add(book)
//                book
//            }
        val book = Book(
            name = requestMap["name"].toString(),
            price = requestMap["price"] as Int
        )
        return bookRepository.save(book)
    }

    fun delete(id: Long): Mono<Void> {
        return Mono.justOrEmpty(books.find { book -> book.id == id })
            .map { book -> books.remove(book) }
            .then()
    }

    fun getBookBy(name: String): Mono<Book> {
        log.info("name = $name")

        val book = bookRepository.findByName(name)
        log.info("book = $book")

        return book

    }
}