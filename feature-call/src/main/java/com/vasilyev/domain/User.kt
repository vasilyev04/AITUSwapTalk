package com.vasilyev.domain

data class User(
    val name: String
){
    constructor(): this(name = "")
}