package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.SaveApp

object PenyediaViewModel {
    val Factory = viewModelFactory {


        initializer {
            HomeMataKuliahViewModel(
                SaveApp().containerApp.repositoryMataKuliah
            )
        }

        initializer {
            DosenViewModel(
                SaveApp().containerApp.repositoryDosen
            )
        }

        initializer {
            HomeDosenViewModel(
                SaveApp().containerApp.repositoryDosen
            )
        }

        initializer {
            MataKuliahViewModel(
                SaveApp().containerApp.repositoryMataKuliah
                ,SaveApp().containerApp.repositoryDosen
            )
        }

        initializer {
            DetailMataKuliahViewModel(
                createSavedStateHandle(),
                SaveApp().containerApp.repositoryMataKuliah
            )
        }

        initializer {
            UpdateMataKuliahViewModel(
                createSavedStateHandle(),
                SaveApp().containerApp.repositoryMataKuliah,
                SaveApp().containerApp.repositoryDosen
            )
        }
    }
}

// Fungsi untuk mendapatkan instance aplikasi
fun CreationExtras.SaveApp(): SaveApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SaveApp)
