package com.example.obopgave.Screen
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

import com.example.obopgave.ViewModel.Beer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeerAdd(
    modifier: Modifier = Modifier,
    addBeer: (Beer) -> Unit = {},
    navigateBack: () -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var abv by remember { mutableStateOf("") }
    var user by remember { mutableStateOf("") }
    var brewery by remember { mutableStateOf("") }
    var style by remember { mutableStateOf("") }
    var volume by remember { mutableStateOf("") }
    var pictureUrl by remember { mutableStateOf("") }
    var howMany by remember { mutableStateOf(0) }
    var nameIsError by remember { mutableStateOf(false) }
    var abvIsError by remember { mutableStateOf(false) }
    Scaffold(modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Add a Beer") })
        }) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {
            // TODO show error message
            val orientation = LocalConfiguration.current.orientation
            val isPortrait = orientation == Configuration.ORIENTATION_PORTRAIT
            // TODO refactor duplicated code: component MyTextField?
            if (isPortrait) {
                OutlinedTextField(onValueChange = { name = it },
                    value = name,
                    isError = nameIsError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Name") })
                OutlinedTextField(onValueChange = { abv = it },
                    value = abv,

                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = abvIsError,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Abv") })
                OutlinedTextField(onValueChange = { user = it },
                    value = user,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "User") })
                OutlinedTextField(onValueChange = { brewery = it },
                    value = brewery,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Brewery") })
                OutlinedTextField(onValueChange = { style = it },
                    value = style,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Style") })
                OutlinedTextField(onValueChange = { volume = it },
                    value = volume,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Volume") })
                OutlinedTextField(onValueChange = { pictureUrl = it },
                    value = pictureUrl,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Picture URL") })
                OutlinedTextField(onValueChange = { howMany = it.toInt() },
                    value = howMany.toString(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "How many") })
            } else {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedTextField(onValueChange = { name = it },
                        value = name,
                        isError = nameIsError,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Name") })
                    OutlinedTextField(onValueChange = { abv = it },
                        value = abv,

                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = abvIsError,
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Abv") })
                    OutlinedTextField(onValueChange = { user = it },
                        value = user,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "User") })
                    OutlinedTextField(onValueChange = { brewery = it },
                        value = brewery,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Brewery") })
                    OutlinedTextField(onValueChange = { style = it },
                        value = style,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Style") })
                    OutlinedTextField(onValueChange = { volume = it },
                        value = volume,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Volume") })
                    OutlinedTextField(onValueChange = { pictureUrl = it },
                        value = pictureUrl,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Picture URL") })
                    OutlinedTextField(onValueChange = { howMany = it.toInt() },
                        value = howMany.toString(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "How many") })

                }
            }
            Row(
                modifier = modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { navigateBack() }) {
                    Text("Back")
                }
                Button(onClick = {
                    if (name.isEmpty()) {
                        nameIsError = true
                        return@Button
                    }
                    if (abv.isEmpty()) {
                        abvIsError = true
                        return@Button
                    }
                    val abv = abv.toDoubleOrNull()
                    if (abv == null) {
                        abvIsError = true
                        return@Button
                    }
                    if (user==""||brewery==""||style==""||volume==""||pictureUrl==""||howMany==0){
                        return@Button
                    }

                    val Beer = Beer(name = name, abv = abv, user = user, brewery = brewery, style = style, volume = volume.toDouble(), pictureUrl = pictureUrl, howMany = howMany)
                    addBeer(Beer)
                    navigateBack()
                }) {
                    Text("Add")
                }
            }
        }
    }
}

@Preview
@Composable
fun BeerAddPreview() {
    BeerAdd()
}