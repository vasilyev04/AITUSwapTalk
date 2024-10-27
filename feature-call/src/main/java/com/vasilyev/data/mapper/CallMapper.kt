package com.vasilyev.data.mapper

import com.vasilyev.data.model.UserDto
import com.vasilyev.domain.model.User

class CallMapper {
    fun mapUserToUserDto(user: User)
            = UserDto(id = user.id, name = user.name)

    fun mapUserDtoToUser(userDto: UserDto)
            = User(id = userDto.id, name = userDto.name)
}