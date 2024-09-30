package com.example.obopgave.Screen

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.obopgave.NavRouters
import com.example.obopgave.ViewModel.Beer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeerListScreen (
        Beers: List<Beer>,
        errorMessage: String,
        modifier: Modifier = Modifier,
        onBeerSelected: (Beer) -> Unit = {},
        onBeerDeleted: (Beer) -> Unit = {},
        onAdd: () -> Unit = {},

    ) {
        //val scaffoldState = rememberScaffoldState()
        Scaffold(modifier = modifier,
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = { Text("Beer list") })
            },
            // TODO in landscape layout, half the button is outside the screen
            // something relating to rememberScaffoldState?
            floatingActionButtonPosition = FabPosition.EndOverlay,
            floatingActionButton = {
                FloatingActionButton(
                    shape = CircleShape,
                    onClick = { onAdd() },
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
                }
            }) { innerPadding ->
            BeerListPanel(
                Beers = Beers,
                modifier = Modifier.padding(innerPadding),
                errorMessage = errorMessage,

                onBeerSelected = onBeerSelected,
                onBeerDeleted = onBeerDeleted,

            )
        }
    }




    @Composable
    private fun BeerListPanel(
        Beers: List<Beer>,
        modifier: Modifier = Modifier,
        errorMessage: String,
              onBeerSelected: (Beer) -> Unit,
        onBeerDeleted: (Beer) -> Unit,

    ) {
        Column(modifier = modifier) {
            if (errorMessage.isNotEmpty()) {
                Text(text = "Problem: $errorMessage")
            }
            val titleUp = "Title \u2191"
            val titleDown = "Title \u2193"
            val priceUp = "Price \u2191"
            val priceDown = "Price \u2193"



            val orientation = LocalConfiguration.current.orientation
            val columns = if (orientation == Configuration.ORIENTATION_PORTRAIT) 1 else 2
            LazyVerticalGrid(
                columns = GridCells.Fixed(columns),
                //modifier = modifier.fillMaxSize()
            ) {
                items(Beers) { Beer ->
                    BeerItem(
                        Beer,
                        onBeerSelected = onBeerSelected,
                        onBeerDeleted = onBeerDeleted
                    )
                }
            }
        }
    }

    @Composable
    private fun BeerItem(
        Beer: Beer,
        modifier: Modifier = Modifier,
        onBeerSelected: (Beer) -> Unit = {},
        onBeerDeleted: (Beer) -> Unit = {}
    ) {
        Card(modifier = modifier
            .padding(4.dp)
            .fillMaxSize(), onClick = { onBeerSelected(Beer) }) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = Beer.name + ": " +  Beer.abv.toString() + " " +  Beer.id.toString()
                )
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Remove",
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { onBeerDeleted(Beer) }
                )
            }
        }
    }


@Preview
@Composable
fun BeerListScreenPreview() {

}