package br.com.app.todolist.domain

data class Todo(
    val id: Long,
    val title: String,
    val description: String?,
    val isCompleted: Boolean
)


val todo1 = Todo(
    1L,
    "Estudar Android",
    "estudar a arquitetura do android e SDK",
    false
)

val todo2 = Todo(
    2L,
    "Ir para a academia",
    "treinar costas",
    false
)

val todo3 = Todo(
    3L,
    "Estudar sistemas distribuidos",
    "fundamentos",
    true
)
