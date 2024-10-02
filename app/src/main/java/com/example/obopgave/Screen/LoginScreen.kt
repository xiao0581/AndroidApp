package com.example.obopgave.Screen



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
import com.example.obopgave.ui.theme.ObopgaveTheme


@OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun LoginScreen(modifier: Modifier = Modifier) {

        Column (modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text( text = "Login" ,
                fontSize = 24.sp, // 设置字体大小
                color = Color.Gray, // 设置字体颜色
                fontWeight = FontWeight.Bold
            )

            Column {
                OutlinedTextField(value = "", onValueChange = { /*TODO*/ }, label = { Text("Username") },shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Green,
                        unfocusedBorderColor = Color.Red
                    ))
                OutlinedTextField(value = "", onValueChange = { /*TODO*/ }, label = { Text("Password") },shape = RoundedCornerShape(10.dp)
                )

                Row {
                    Spacer(modifier = Modifier.width(90.dp))
                    Button(onClick = {
                    }) {
                        Text("Register")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(onClick = {
                    }) {
                        Text("Login")
                    }
                }
            }
        }
    }
@Preview
@Composable
fun PreviewLoginScreen() {
    ObopgaveTheme {
        LoginScreen()
    }
}
