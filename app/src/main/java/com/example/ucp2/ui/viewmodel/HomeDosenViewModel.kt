package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.repository.RepositoryDosen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class HomeDosenViewModel(
    private val repositoryDosen: RepositoryDosen
) : ViewModel() {

    val homeUiState: StateFlow<HomeDosenUiState> = repositoryDosen.getAllDosen()
        .filterNotNull()
        .map {
            HomeDosenUiState(
                listDosen = it.toList(),
                isLoading = false
            )
        }
        .onStart {
            emit(HomeDosenUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeDosenUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeDosenUiState(isLoading = true)
        )
}

data class HomeDosenUiState(
    val listDosen: List<Dosen> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)
