package br.com.app.todolist.ui.features.addedit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.app.todolist.data.TodoDatabaseProvider
import br.com.app.todolist.data.TodoRepositoryImpl
import br.com.app.todolist.ui.UiEvent
import br.com.app.todolist.ui.theme.ToDoListTheme

@Composable
fun AddEditScreen(
    navigateBack: () -> Unit
) {
    val context = LocalContext.current.applicationContext
    val database = TodoDatabaseProvider.provide(context)
    val repository = TodoRepositoryImpl(dao = database.todoDao)
    val viewModel = viewModel<AddEditViewModel> {
        AddEditViewModel(
            repository = repository
        )
    }
    val title = viewModel.title
    val description = viewModel.description

    val snackBarHostState = remember {
        SnackbarHostState()
    }


    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when(uiEvent) {
                is UiEvent.ShowSnackbar -> {
                    snackBarHostState.showSnackbar(
                        message = uiEvent.message
                    )
                }
                UiEvent.NavigateBack -> {
                    navigateBack()
                }

                else -> {}
            }

        }

    }

    AddEditContent(
        title = title,
        description = description,
        onEvent = viewModel::onEvent,
        snackBarHostState = snackBarHostState
    )
}

@Composable
fun AddEditContent(
    title: String,
    description: String?,
    onEvent: (AddEditEvent) -> Unit,
    snackBarHostState: SnackbarHostState
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(
                    AddEditEvent.Save
                )
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "check")
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) {
        Column(
            modifier = Modifier.consumeWindowInsets(it),
            
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                value = title,
                onValueChange = {
                    onEvent(
                        AddEditEvent.TitleChanged(it)
                    )
                },
                placeholder = {
                    Text(text = "Title")
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                value = description ?: "",
                onValueChange = {
                    onEvent(
                        AddEditEvent.DescriptionChanged(it)
                    )
                },
                placeholder = {
                    Text("Description (Optional)")
                }
            )
        }
    }
}

@Preview
@Composable
private fun AddEditContentPreview() {
    ToDoListTheme {
        AddEditContent(
            title = "",
            description = null,
            snackBarHostState = SnackbarHostState(),
            onEvent = {}
        )
    }
}