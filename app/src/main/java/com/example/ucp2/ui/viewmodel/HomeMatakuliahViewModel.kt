package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ucp2.repository.RepositoryMatkul
import kotlinx.coroutines.flow.StateFlow

class HomeMatakuliahViewModel(
    private val repositoryMatkul: RepositoryMatkul

): ViewModel() {

    val homeUiState: StateFlow<HomeUiState>
}