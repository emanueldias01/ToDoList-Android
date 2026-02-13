package br.com.app.todolist.ui.components

import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.app.todolist.domain.Todo
import br.com.app.todolist.domain.todo1
import br.com.app.todolist.domain.todo3
import br.com.app.todolist.ui.theme.ToDoListTheme

@Composable
fun ToDoItem(
    modifier: Modifier = Modifier,
    onCompletedChange: (Boolean) -> Unit,
    onItemClick: () -> Unit,
    onDeleteClick: () -> Unit,
    item: Todo
) {
    Surface(
        onClick = onItemClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 2.dp,
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.outline
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = item.isCompleted, onCheckedChange = onCompletedChange)

            Spacer(modifier = modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleLarge
                )

                item.description?.let {
                    Spacer(modifier = modifier.height(8.dp))

                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = modifier.width(8.dp))

            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
            }
        }
    }
}

@Preview
@Composable
private fun ToDoItemCheckPreview() {
    ToDoListTheme {
        ToDoItem(
            item = todo3,
            onItemClick = {},
            onDeleteClick = {},
            onCompletedChange = {}
        )
    }
}

@Preview
@Composable
private fun ToDoItemUncheckedPreview() {
    ToDoListTheme {
        ToDoItem(
            item = todo1,
            onItemClick = {},
            onDeleteClick = {},
            onCompletedChange = {}
        )
    }
}