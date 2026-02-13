package br.com.app.todolist.ui.features

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.app.todolist.ui.theme.ToDoListTheme

@Composable
fun AddEditScreen(modifier: Modifier = Modifier) {

}

@Composable
fun AddEditContent(modifier: Modifier = Modifier) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "check")
            }
        }
    ) {
        Column(
            modifier = modifier.consumeWindowInsets(it),
            
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(text = "Title")
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                value = "",
                onValueChange = {},
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
        AddEditContent()
    }
}