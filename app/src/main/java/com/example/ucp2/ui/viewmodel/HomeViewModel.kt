package com.example.ucp2.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praktikum9.repository.RepositoryDosen
import com.example.ucp2.Data.entity.Dosen
import com.example.ucp2.Data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryMataKuliah
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val repositoryDosen: RepositoryDosen,
    private val repositoryMataKuliah: RepositoryMataKuliah
) : ViewModel(){

    val HomeUiState: StateFlow<HomeUiState> = combine(repositoryDosen.getAllDosen().filterNotNull(),
        repositoryMataKuliah.getAllMataKuliah().filterNotNull())

         {  dosenlist, matakuliahlist ->
            HomeUiState(
                listDosen = dosenlist.toList(),
                listMataKuliah = matakuliahlist.toList(),
                isLoading = false
            )
        }
        .onStart {
            emit(HomeUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState(
                isLoading = true,
            )
        )
}

data class HomeUiState(
    val listDosen: List<Dosen> = listOf(),
    val listMataKuliah: List<MataKuliah> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)