package com.example.ucp2.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Menu(
    onHalamanMenuDosen: () -> Unit,
    onHalamanMenuMK: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {onHalamanMenuDosen()},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Dosen")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {onHalamanMenuMK()},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Matakuliah")
            }
        }
}