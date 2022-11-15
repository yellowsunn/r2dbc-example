package com.yellowsunn.r2dbcexample.dto

data class TodosDto(
    val userId: Long,
    val name: String,
    val todos: List<Todo>,
) {
    data class Todo(
        val todoId: Long,
        val title: String,
        val body: String,
    )
}

