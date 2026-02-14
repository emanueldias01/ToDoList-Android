package br.com.app.todolist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import br.com.app.todolist.ui.features.AddEditScreen
import br.com.app.todolist.ui.features.ListScreen
import kotlinx.serialization.Serializable


@Serializable
object ListRoute

@Serializable
data class AddEditRoute(val id: Long? = null)

@Composable
fun TodoNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ListRoute) {
        composable<ListRoute> { ListScreen(
            navigateToAddEditScreen = { id ->
                navController.navigate(AddEditRoute(id))

            }
        ) }
        composable<AddEditRoute> {
            val addEditRoute = it.toRoute<AddEditRoute>()
            AddEditScreen()
        }
    }
}

