package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.SaveApp

object PenyediaViewModel {
    val Factory = viewModelFactory {
        // Initializer untuk Dosen
        initializer {
            HomeViewModel(
                SaveApp().containerApp.repositoryDosen,
                SaveApp().containerApp.repositoryMataKuliah
            )
        }

        // Initializer untuk MataKuliah
        initializer {
            MataKuliahViewModel(
                SaveApp().containerApp.repositoryMataKuliah
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
                SaveApp().containerApp.repositoryMataKuliah
            )
        }
    }
}

// Fungsi untuk mendapatkan instance aplikasi
fun CreationExtras.SaveApp(): SaveApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SaveApp)
