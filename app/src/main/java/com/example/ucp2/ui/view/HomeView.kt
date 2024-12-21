package com.example.ucp2.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.ucp2.ui.viewmodel.HomeUiState
import com.example.ucp2.ui.viewmodel.HomeViewModel
import com.example.ucp2.ui.viewmodel.PenyediaViewModel


private val Typography.h6: TextStyle
    get() = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 28.sp,
        letterSpacing = 0.15.sp
    )

@Composable
fun HomeView(
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddDosen: () -> Unit = { },
    onDetailClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                onBack = { },
                showBackButton = false,
                judul = "Daftar Dosen & Mata Kuliah",
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
        val homeUiState by viewModel.HomeUiState.collectAsState()

        BodyHomeDosenView(
            homeUiState = homeUiState,
            onDosenClick = { onDetailClick(it) },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeDosenView(
    homeUiState: HomeUiState,
    onDosenClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
) {
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

        homeUiState.listDosen.isEmpty() && homeUiState.listMataKuliah.isEmpty() -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada data.",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }

        else -> {
            LazyColumn(modifier = modifier) {
                item {
                    Text(
                        text = "Daftar Dosen",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                items(homeUiState.listDosen) { dosen ->
                    CardDosen(
                        dosen = dosen,
                        onClick = { onDosenClick(dosen.nidn.toString()) }
                    )
                }

                item {
                    Text(
                        text = "Daftar Mata Kuliah",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                items(homeUiState.listMataKuliah) { mataKuliah ->
                    CardMataKuliah(mataKuliah = mataKuliah)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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

@Composable
fun CardMataKuliah(
    mataKuliah: MataKuliah,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = mataKuliah.nama,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = "SKS: ${mataKuliah.sks}",
                fontSize = 16.sp
            )
        }
    }
}

