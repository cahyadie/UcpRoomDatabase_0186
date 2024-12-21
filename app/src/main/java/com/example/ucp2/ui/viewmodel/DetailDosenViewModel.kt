package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praktikum9.repository.RepositoryDosen
import com.example.ucp2.Data.entity.Dosen
import com.example.ucp2.ui.Navigation.Navigasi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn


class DetailDosenViewModel (
    savedStatehandle: SavedStateHandle,
    private val repositoryDosen: RepositoryDosen,
): ViewModel() {
    private val _nidn: String = checkNotNull(savedStatehandle[Navigasi.DestinasiDetail.Nidn])
    val detailUiStateDosen: StateFlow<DetailUiStateDosen> = repositoryDosen.getDosen(_nidn)
        .filterNotNull()
        .map {
            DetailUiStateDosen(
                detailUiEvent = it.toDetailUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailUiStateDosen(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailUiStateDosen(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiStateDosen(
                isLoading = true,
            ),
        )

}


data class DetailUiStateDosen(
    val detailUiEvent: DosenEvent = DosenEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == DosenEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != DosenEvent()
}


fun Dosen.toDetailUiEvent():DosenEvent{
    return DosenEvent(
        nidn = nidn,
        nama = nama,
        jenisKelamin = jenisKelamin,
    )
}