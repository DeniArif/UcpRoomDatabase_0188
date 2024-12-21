package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ucp2.entity.Matakuliah
import com.example.ucp2.repository.RepositoryMatkul
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class HomeMatakuliahViewModel(
    private val repositoryMatkul: RepositoryMatkul

): ViewModel() {

    val homeUiState: StateFlow<HomeUiState> = repositoryMatkul.getAllMatakuliah()
        .filterNotNull()
        .map {
            HomeUiState(
                listMatakuliah = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(HomeUiState(isLoading = true))
            delay(900)
        }
}

data class HomeUiState(
    val listMatakuliah: List<Matakuliah> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)