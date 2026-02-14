package br.com.app.todolist.ui.features.list

sealed interface ListEvent {
    data class Delete(val id: Long) : ListEvent
    data class CompletedChange(val id: Long, val completed: Boolean) : ListEvent
    data class AddEdit(val id: Long?) : ListEvent

}