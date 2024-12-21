package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.KrsApp

object PenyediaViewModel{

    val Factory = viewModelFactory {
        initializer {
            MatakuliahViewModel(
                KrsApp().containerApp.repositoryMatkul
            )
        }

        initializer {
            HomeMatakuliahViewModel(
                KrsApp().containerApp.repositoryMatkul
            )
        }

        initializer {
            DetailMatakuliahViewModel(
                createSavedStateHandle(),
                KrsApp().containerApp.repositoryMatkul
            )
        }

        initializer {
            UpdateMatakuliahViewModel(
                createSavedStateHandle(),
                KrsApp().containerApp.repositoryMatkul
            )
        }
    }

}