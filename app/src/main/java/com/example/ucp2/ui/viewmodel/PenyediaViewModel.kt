package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.KrsApp
import com.example.ucp2.ui.viewdosen.InsertDosenView

object PenyediaViewModel{

    val Factory = viewModelFactory {
        initializer {
            MatakuliahViewModel(
                KrsApp().containerApp.repositoryMatkul,
                KrsApp().containerApp.repositoryDosen
            )
        }

        initializer {
            HomeMatakuliahViewModel(
                KrsApp().containerApp.repositoryMatkul,
                KrsApp().containerApp.repositoryDosen

            )
        }
        initializer {
            HomeDosenViewModel(
                KrsApp().containerApp.repositoryDosen
            )
        }

        initializer {
            DetailMatakuliahViewModel(
                createSavedStateHandle(),
                KrsApp().containerApp.repositoryMatkul
            )
        }

        initializer {
            DosenViewModel(

                KrsApp().containerApp.repositoryDosen
            )
        }

        initializer {
            UpdateMatakuliahViewModel(
                createSavedStateHandle(),
                KrsApp().containerApp.repositoryMatkul,
                KrsApp().containerApp.repositoryDosen
            )
        }
    }
    fun CreationExtras.KrsApp(): KrsApp =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KrsApp)

}