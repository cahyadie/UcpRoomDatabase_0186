package com.example.ucp2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.repository.RepositoryDosen
import com.example.ucp2.Data.entity.Dosen
import com.example.ucp2.Data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryMataKuliah
import com.example.ucp2.ui.Navigation.Navigasi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class UpdateMataKuliahViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryMataKuliah: RepositoryMataKuliah,
    private val repositoryDosen: RepositoryDosen
) : ViewModel() {

    val dosenState: StateFlow<List<Dosen>> = repositoryDosen.getAllDosen()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )



    var updateUIState by mutableStateOf(MataKuliahUiState())
        private set

    private val _kode: String = checkNotNull(savedStateHandle[Navigasi.DestinasiUpdateMataKuliah.Kode])

    init {
        viewModelScope.launch {
            updateUIState = repositoryMataKuliah.getMataKuliah(_kode)
                .filterNotNull()
                .first()
                .toUIStateMataKuliah()
        }
    }

    fun updateState (mataKuliahEvent: MataKuliahEvent) {
        updateUIState = updateUIState.copy(
            mataKuliahEvent = mataKuliahEvent,
        )
    }

    fun validateFields(): Boolean {
        val event = updateUIState.mataKuliahEvent
        val errorState = FormErrorState2(
            kode = if (event.kode.isNotEmpty()) null else "kode tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "sks tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "semester tidak boleh kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "jenis tidak boleh kosong",
            dosenpengampu = if (event.dosenpengampu.isNotEmpty()) null else "dosenpengampu tidak boleh kosong"
        )

        updateUIState = updateUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateData() {
        val currentEvent = updateUIState.mataKuliahEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMataKuliah.updateMataKuliah(currentEvent.toMataKuliahEntity())
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data berhasil diupdate",
                        mataKuliahEvent = MataKuliahEvent(),
                        isEntryValid = FormErrorState2()
                    )
                    println("snackBarMessage diatur: ${updateUIState.snackBarMessage}")
                } catch (e: Exception) {
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data gagal diupdate"
                    )
                }
            }
        } else {
            updateUIState = updateUIState.copy(
                snackBarMessage = "Data gagal diupdate"
            )
        }
    }
    fun resetSnackBarMessage() {
        updateUIState = updateUIState.copy(snackBarMessage = null)
    }

}

fun MataKuliah.toUIStateMataKuliah(): MataKuliahUiState = MataKuliahUiState(
    mataKuliahEvent = this.toDetailUiEvent(),
)