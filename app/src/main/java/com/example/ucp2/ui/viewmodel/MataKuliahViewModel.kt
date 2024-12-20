package com.example.ucp2.ui.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.Data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryMataKuliah
import kotlinx.coroutines.launch


class MataKuliahViewModel(private val repositoryMataKuliah: RepositoryMataKuliah) : ViewModel(){
    var uiState by mutableStateOf(MataKuliahUiState())

    fun updateState(mataKuliahEvent: MataKuliahEvent){
        uiState = uiState.copy(
            mataKuliahEvent = mataKuliahEvent,
        )
    }

    @SuppressLint("SuspiciousIndentation")
    private fun validateFields(): Boolean {
        val event = uiState.mataKuliahEvent
        val errorState = FormErrorState2(
            kode = if (event.kode.isNotEmpty()) null else "kode tidak boleh kosong",
            nama = if (event.kode.isNotEmpty()) null else "nama tidak boleh kosong",
            sks = if (event.kode.isNotEmpty()) null else "sks tidak boleh kosong",
            semester = if (event.kode.isNotEmpty()) null else "semester tidak boleh kosong",
            jenis = if (event.kode.isNotEmpty()) null else "jenis tidak boleh kosong",
            dosenpengampu = if (event.kode.isNotEmpty()) null else "dosenpengampu tidak boleh kosong"
            )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData(){
        val currentEvent = uiState.mataKuliahEvent
        if (validateFields()){
            viewModelScope.launch {
                try {
                    repositoryMataKuliah.insertMataKuliah(currentEvent.toMataKuliahEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        mataKuliahEvent = MataKuliahEvent(),
                        isEntryValid = FormErrorState2()
                    )
                }catch (e: Exception){
                    uiState = uiState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackBarMessage = "Input tidak Valid, periksa kembali data anda"
            )
        }
    }
    fun resetSncakBarMessage(){
        uiState = uiState.copy(snackBarMessage = null)
    }
}


data class MataKuliahUiState(
    val mataKuliahEvent: MataKuliahEvent = MataKuliahEvent(),
    val isEntryValid: FormErrorState2 = FormErrorState2(),
    val snackBarMessage: String? = null,
)



data class FormErrorState2(
    val kode: String? = null,
    val nama: String? = null,
    val sks: String? = null,
    val semester: String? = null,
    val jenis: String? = null,
    val dosenpengampu: String? = null
){
    fun isValid(): Boolean {
        return kode == null && nama == null && sks == null && semester == null && jenis == null
                && dosenpengampu == null
    }
}

fun MataKuliahEvent.toMataKuliahEntity(): MataKuliah = MataKuliah(
    kode = kode,
    nama = nama,
    sks = sks,
    semester = semester,
    jenis = jenis,
    dosenpengampu = dosenpengampu
)

data class MataKuliahEvent(
    val kode: String = "",
    val nama: String = "",
    val sks: String = "",
    val semester: String = "",
    val jenis: String = "",
    val dosenpengampu: String = ""
)