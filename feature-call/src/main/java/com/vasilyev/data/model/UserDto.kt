package com.vasilyev.data.model

import com.vasilyev.firebase.FirebaseEntity

data class UserDto(
    override var id: String = UNDEFINED_ID,
    val name: String
): FirebaseEntity(id = id){
    companion object {
        private const val UNDEFINED_ID = "UNDEFINED"
    }

    constructor(): this(id = "", name = "")
}