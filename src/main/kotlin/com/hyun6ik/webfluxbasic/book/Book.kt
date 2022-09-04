package com.hyun6ik.webfluxbasic.book

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table
data class Book(

    @Column
    val name: String,

    @Column
    val price: Int,

    @Id
    val id: Long? = null,
)
