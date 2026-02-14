package br.com.app.todolist.ui.features.addedit

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.app.todolist.data.TodoRepository
import br.com.app.todolist.ui.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditViewModel(
    private val repository: TodoRepository
) : ViewModel() {
    var title by mutableStateOf("")

    var description by mutableStateOf<String?>(null)

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AddEditEvent) {
        when(event) {
            is AddEditEvent.TitleChanged -> {
                title = event.title
            }
            is AddEditEvent.DescriptionChanged -> {
                description = event.description
            }
            is AddEditEvent.Save -> {
                saveTodo()
            }
        }
    }

    private fun saveTodo() {
        viewModelScope.launch {
            if(title.isBlank()) {
                _uiEvent.send(UiEvent.ShowSnackbar("Título não pode ser vazio"))
                return@launch
            }
            repository.insert(title, description)
            _uiEvent.send(UiEvent.NavigateBack)
        }
    }
}