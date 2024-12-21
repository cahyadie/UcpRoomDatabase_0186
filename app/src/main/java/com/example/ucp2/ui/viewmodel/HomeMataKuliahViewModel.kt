package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.Data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryMataKuliah
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn


class HomeMataKuliahViewModel (
    private val repositoryMataKuliah: RepositoryMataKuliah
) : ViewModel() {
    val homeUiState: StateFlow<HomeMataKuliahState> = repositoryMataKuliah.getAllMataKuliah()
        .filterNotNull()
        .map {
            HomeMataKuliahState (
                listMataKuliah = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(HomeMataKuliahState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeMataKuliahState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeMataKuliahState(
                isLoading = true,
            )
        )
}

data class HomeMataKuliahState(
    val listMataKuliah: List<MataKuliah> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)