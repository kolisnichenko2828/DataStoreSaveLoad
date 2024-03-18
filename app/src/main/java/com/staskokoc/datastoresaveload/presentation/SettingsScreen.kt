package com.staskokoc.datastoresaveload.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.staskokoc.datastoresaveload.datastore.AppSettings
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val appSettings by context.dataStore.data.collectAsState(initial = AppSettings())
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var language by rememberSaveable { mutableStateOf(appSettings.language) }
        LaunchedEffect(appSettings.language) {
            language = appSettings.language
        }
        OutlinedTextField(
            value = language,
            onValueChange = { newName -> language = newName },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(all = 4.dp),
            textStyle = TextStyle(fontSize = 18.sp),
            singleLine = true,
            placeholder = { Text("Enter your language") },
            shape = RoundedCornerShape(4.dp),
        )
        var name by rememberSaveable { mutableStateOf(appSettings.name) }
        LaunchedEffect(appSettings.name) {
            name = appSettings.name
        }
        OutlinedTextField(
            value = name,
            onValueChange = { newSurname -> name = newSurname },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(all = 4.dp),
            textStyle = TextStyle(fontSize = 18.sp),
            singleLine = true,
            placeholder = { Text("Enter your name") },
            shape = RoundedCornerShape(4.dp),
        )
        OutlinedButton(
            modifier = Modifier
                .padding(all = 4.dp)
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(4.dp),
            onClick = {
                coroutineScope.launch {
                    context.dataStore.updateData {
                        it.copy(
                            language = language,
                            name = name
                        )
                    }
                }
            }
        ) {
            Text(
                text = "SAVE",
                style = TextStyle(fontSize = 18.sp)
            )
        }
    }
}