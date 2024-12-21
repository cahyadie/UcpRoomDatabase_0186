package com.example.ucp2.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.Navigation.Navigasi
import com.example.ucp2.ui.costumwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.FormErrorState2
import com.example.ucp2.ui.viewmodel.MataKuliahEvent
import com.example.ucp2.ui.viewmodel.MataKuliahUiState
import com.example.ucp2.ui.viewmodel.MataKuliahViewModel
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch


object DestinasiInsertMataKuliah : Navigasi {
    override val route: String = "insert_mataKuliah"
}


@Composable
fun InsertMataKuliahView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MataKuliahViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSncakBarMessage()
            }
        }
    }
    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah MataKuliah",
                modifier = modifier
            )
            InsertMataKuliah(
                uiState = uiState,
                onValueChange = {updateEvent ->
                    viewModel.updateState(updateEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveData()
                    }
                    onNavigate()
                }
            )
        }
    }
}


@Composable
fun InsertMataKuliah(
    modifier: Modifier = Modifier,
    onValueChange: (MataKuliahEvent) -> Unit,
    uiState: MataKuliahUiState,
    onClick:() -> Unit
){
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormMataKuliah(
            mataKuliahEvent = uiState.mataKuliahEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Simpan")
        }
    }
}


@Composable
fun FormMataKuliah(
    mataKuliahEvent: MataKuliahEvent = MataKuliahEvent(),
    onValueChange:(MataKuliahEvent) -> Unit = {},
    errorState: FormErrorState2 = FormErrorState2(),
    listDosen: List<String> = listOf("Dr. Budi", "Prof. Siti", "Dr. Ahmad"),
    modifier: Modifier = Modifier
){
    val jenis =  listOf("Wajib", "Pilihan")
    var expanded by remember { mutableStateOf(false) }
    var selectedDosen by remember { mutableStateOf(mataKuliahEvent.dosenpengampu)}

    Column(modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mataKuliahEvent.kode,
            onValueChange = {
                onValueChange(mataKuliahEvent.copy(kode = it))
            },
            label = { Text("Kode Mata Kuliah") },
            isError = errorState.kode != null,
            placeholder = { Text("Masukkan kode mata kuliah") },
        )
        Text(
            text = errorState.kode ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mataKuliahEvent.nama,
            onValueChange = {
                onValueChange(mataKuliahEvent.copy(nama = it))
            },
            label = { Text("Nama Mata Kuliah") },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan nama mata kuliah") },
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mataKuliahEvent.sks,
            onValueChange = {
                onValueChange(mataKuliahEvent.copy(sks = it))
            },
            label = { Text("SKS") },
            isError = errorState.sks != null,
            placeholder = { Text("Masukkan jumlah SKS") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(
            text = errorState.sks ?: "",
            color = Color.Red
        )


        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = mataKuliahEvent.semester,
            onValueChange = {
                onValueChange(mataKuliahEvent.copy(semester = it))
            },
            label = { Text("Semester") },
            isError = errorState.semester != null,
            placeholder = { Text("Masukkan semester") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(
            text = errorState.semester ?: "",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Jenis Mata Kuliah")
        Row(modifier = Modifier.fillMaxWidth()) {
            jenis.forEach { jenis ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = mataKuliahEvent.jenis == jenis,
                        onClick = {
                            onValueChange(mataKuliahEvent.copy(jenis = jenis))
                        },
                    )
                    Text(text = jenis)
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Dosen Pengampu")

        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = selectedDosen,
                onValueChange = {},
                label = { Text("Dosen Pengampu") },
                isError = errorState.dosenpengampu != null,
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true },
                trailingIcon = {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.ArrowDropDown else Icons.Filled.ArrowDropDown,
                        contentDescription = null
                    )
                }
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            listDosen.forEach { dosen ->
                DropdownMenuItem(
                    text = { Text(text = dosen) },
                    onClick = {
                        selectedDosen = dosen
                        onValueChange(mataKuliahEvent.copy(dosenpengampu = dosen))
                        expanded = false
                    }
                )
            }
        }

        Text(
            text = errorState.dosenpengampu ?: "",
            color = Color.Red
        )
    }
}