package br.com.app.todolist.ui.features

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.app.todolist.domain.Todo
import br.com.app.todolist.domain.todo1
import br.com.app.todolist.domain.todo2
import br.com.app.todolist.domain.todo3
import br.com.app.todolist.ui.components.ToDoItem
import br.com.app.todolist.ui.theme.ToDoListTheme

@Composable
fun ListScreen(modifier: Modifier = Modifier) {

}

@Composable
fun ListContent(modifier: Modifier = Modifier, todos: List<Todo>) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(Icons.Default.Add, contentDescription = "add")
            }
        }
    ) {

        LazyColumn(
            modifier = modifier.consumeWindowInsets(it),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(todos) {
                ToDoItem(
                    item = it,
                    onCompletedChange = {},
                    onDeleteClick = {},
                    onItemClick = {}
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
            todos = listOf(todo1, todo2, todo3)
        )
    }
}