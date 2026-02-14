package br.com.app.todolist.ui.features.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.app.todolist.data.TodoDatabaseProvider
import br.com.app.todolist.data.TodoRepositoryImpl
import br.com.app.todolist.domain.Todo
import br.com.app.todolist.domain.todo1
import br.com.app.todolist.domain.todo2
import br.com.app.todolist.domain.todo3
import br.com.app.todolist.navigation.AddEditRoute
import br.com.app.todolist.ui.UiEvent
import br.com.app.todolist.ui.components.ToDoItem
import br.com.app.todolist.ui.theme.ToDoListTheme

@Composable
fun ListScreen(
    navigateToAddEditScreen: (id: Long?) -> Unit
) {

    val context = LocalContext.current.applicationContext
    val database = TodoDatabaseProvider.provide(context)
    val repository = TodoRepositoryImpl(dao = database.todoDao)
    val viewModel = viewModel<ListViewModel> {
        ListViewModel(
            repository = repository
        )
    }

    val todos by viewModel.todos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate<*> -> {
                    when (uiEvent.route) {
                        is AddEditRoute -> {
                            navigateToAddEditScreen(uiEvent.route.id)
                        }

                        UiEvent.NavigateBack -> {
                            //TODO
                        }

                        is UiEvent.ShowSnackbar -> {
                            //TODO
                        }
                    }
                }

                else -> {}
            }

        }
    }
    ListContent(
        todos = todos,
        onEvent = viewModel::onEvent,
    )
}
@Composable
fun ListContent(
    modifier: Modifier = Modifier,
    todos: List<Todo>,
    onEvent: (ListEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(
                    ListEvent.AddEdit(id = null)
                )
            }) {
                Icon(Icons.Default.Add, contentDescription = "add")
            }
        }
    ) {

        LazyColumn(
            modifier = modifier.consumeWindowInsets(it),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(todos) { todo ->
                ToDoItem(
                            item = todo,
                            onCompletedChange = { isChecked ->
                                onEvent(
                                    ListEvent.CompletedChange(todo.id, isChecked)
                                )
                            },
                            onDeleteClick = {
                                onEvent(
                                    ListEvent.Delete(todo.id)
                                )
                            },
                            onItemClick = {
                                onEvent(
                                    ListEvent.AddEdit(todo.id)
                                )
                            }
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}





@Preview
@Composable
private fun ListContentPreview() {
    ToDoListTheme {
        ListContent(
            todos = listOf(todo1, todo2, todo3),
            onEvent = {}
        )
    }
}

