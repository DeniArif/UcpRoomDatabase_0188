package com.example.ucp2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Matakuliah
import com.example.ucp2.repository.RepositoryMatkul
import com.example.ucp2.ui.navigation.DestinasiUpdateMatkul

import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMatakuliahViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMatkul: RepositoryMatkul
): ViewModel() {
    var updateUIState by mutableStateOf(MtkUIState())
        private set

    private val _kode: String = checkNotNull(savedStateHandle[DestinasiUpdateMatkul.KODE])


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
            kode = if (event.kode.isNotEmpty()) null else "Kode Tidak Boleh Kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama Tidak Boleh Kosong",
            sks = if (event.sks.isNotEmpty()) null else "SKS Tidak Boleh Kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester Tidak Boleh Kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "Jenis Tidak Boleh Kosong",
            dosenpengampu = if (event.dosenpengampu.isNotEmpty()) null else "Dosen Pengampu Tidak Boleh Kosong",
        )
        updateUIState = updateUIState.copy(isentryValid = errorState)
        return errorState.isValidMatkul()
    }

    fun updateData() {
        val currentEvent = updateUIState.matakuliahEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMatkul.updateMatakuliah(currentEvent.toMatakuliahEntity())
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data Berhasil Diupdate",
                        matakuliahEvent = MatakuliahEvent(),
                        isentryValid = FormErrorStateMatkul()
                    )
                    println("snackBarMessage diatur: ${updateUIState.snackBarMessage}")
                }catch( e: Exception){
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data gagal diupdate"
                    )
                }
            }
        } else {
            updateUIState = updateUIState.copy(
                snackBarMessage = "Data gagal diupdate"
            )
        }
    }
    fun resetSnackBarMessage(){
        updateUIState = updateUIState.copy(snackBarMessage = null)
    }
}

fun Matakuliah.toUIStateMatkul(): MtkUIState = MtkUIState(
    matakuliahEvent = this.toDetailUiEvent()
)