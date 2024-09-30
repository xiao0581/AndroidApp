package com.example.obopgave

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.obopgave.Screen.BeerListScreen
import com.example.obopgave.Screen.UserbeerScreen
import com.example.obopgave.ViewModel.Beer
import com.example.obopgave.ViewModel.BeerViewModel
import com.example.obopgave.ui.theme.ObopgaveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ObopgaveTheme {
                MainScreen()
            }
        }
    }
}
    @Composable
    fun MainScreen(modifier: Modifier = Modifier) {
        val navController = rememberNavController()
        val viewModel: BeerViewModel = viewModel() // persistence
        val Beers = viewModel.BeersFlow.value
        val errorMessage = viewModel.errorMessageFlow.value

        NavHost(navController = navController, startDestination = NavRouters.BeerListScreen.route) {
            composable(NavRouters.BeerListScreen.route) {
                BeerListScreen(
                    Beers = Beers,
                    errorMessage = errorMessage,
                    modifier = modifier,
                    onBeerSelected = { beer -> navController.navigate(NavRouters.UserPageScreen.route) },
                    onBeerDeleted = { beer -> viewModel.remove(beer) },
                    onAdd = { navController.navigate(NavRouters.UserPageScreen.route) }
                )
            }
        }
    }
@Preview
@Composable
fun DefaultPreview() {
    ObopgaveTheme {
        MainScreen()
    }
}





