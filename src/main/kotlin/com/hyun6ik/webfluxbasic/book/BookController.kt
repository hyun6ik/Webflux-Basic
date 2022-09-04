package com.hyun6ik.webfluxbasic.book

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class BookController(
    private val bookService: BookService,
) {

    @GetMapping("/books")
    fun getAll(): Flux<Book> {
        return bookService.getAll()
    }

    @GetMapping("/books/{id}")
    fun get(@PathVariable id: Int): Mono<Book> {
        return bookService.getBook(id)
    }

    @PostMapping("/books")
    fun create(@RequestBody request: Map<String, Any>): Mono<Book> {
        return bookService.create(request)
    }

    @DeleteMapping("/books/{id}")
    fun delete(@PathVariable id: Int) : Mono<Void> {
        return bookService.delete(id)
    }
}