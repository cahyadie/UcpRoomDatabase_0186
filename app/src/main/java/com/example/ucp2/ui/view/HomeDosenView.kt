package com.example.ucp2.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.Data.entity.Dosen
import com.example.ucp2.Data.entity.MataKuliah
import com.example.ucp2.ui.costumwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.HomeDosenState
import com.example.ucp2.ui.viewmodel.HomeDosenViewModel
import com.example.ucp2.ui.viewmodel.PenyediaViewModel

@Composable
fun HomeDosenView(
    viewModel: HomeDosenViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddDosen: () -> Unit = { },
    onBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                showBackButton = true,
                judul = "Daftar Dosen",
                onBack = onBack,
                modifier = Modifier
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddDosen,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Dosen",
                )
            }
        }
    ) { innerPadding ->
        val homeUiState by viewModel.homeUiState.collectAsState()

        BodyHomeDosenView(
            homeUiState = homeUiState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeDosenView(
    homeUiState: HomeDosenState,
    onClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
) {

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when {
        homeUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        homeUiState.isError -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Terjadi Kesalahan: ${homeUiState.errorMessage}",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        else -> {
            ListDosen(
                listDsn = homeUiState.listDosen,
                onClick = {
                    onClick(it)
                    println(
                        it
                    )
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListDosen(
    listDsn: List<Dosen>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
) {
    LazyColumn (
        modifier = modifier
    ) {
        items(
            items = listDsn,
            itemContent = { dosen ->
                CardDosen(
                    dosen = dosen,
                    onClick = { onClick(dosen.nidn)}
                )
            }
        )
    }
}

@OptIn
@Composable
fun CardDosen(
    dosen: Dosen,
    onClick: () -> Unit = { },
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = dosen.nama,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = "ID: ${dosen.nama}",
                fontSize = 16.sp
            )
        }
    }
}