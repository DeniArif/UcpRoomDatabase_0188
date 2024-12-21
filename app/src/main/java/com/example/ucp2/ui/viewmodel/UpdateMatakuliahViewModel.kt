package com.example.ucp2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.repository.RepositoryMatkul
import com.example.ucp2.ui.navigation.DestinasiUpdate
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMatakuliahViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMatkul: RepositoryMatkul
): ViewModel() {
    var updateUIState by mutableStateOf(MtkUIState())
        private set

    private val _kode: String = checkNotNull(savedStateHandle[DestinasiUpdate.KODE])


    init {
        viewModelScope.launch {
            updateUIState = repositoryMatkul.getMatakuliah((_kode))
                .filterNotNull()
                .first()
                .toUIStateMatkul()
        }
    }

    fun updateState(matakuliahEvent: MatakuliahEvent) {
        updateUIState = updateUIState.copy(
            matakuliahEvent = matakuliahEvent
        )
    }

    fun validateFields(): Boolean{
        val event = updateUIState.matakuliahEvent
        val errorState = FormErrorStateMatkul(
            kode = if (event.kode.i)
        )
    }
}