package com.example.ucp2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.data.entity.Matakuliah
import com.example.ucp2.repository.RepositoryDosen
import com.example.ucp2.repository.RepositoryMatkul
import com.example.ucp2.ui.navigation.DestinasiUpdateMatkul
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMatakuliahViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMatkul: RepositoryMatkul,
    private val repositoryDosen: RepositoryDosen
) : ViewModel() {

    var updateUIState by mutableStateOf(MtkUIState())
        private set

    var dosenList by mutableStateOf(emptyList<Dosen>())
        private set

    private val _kode: String = checkNotNull(savedStateHandle[DestinasiUpdateMatkul.KODE])

    // Fetch matakuliah data and dosen list on initialization
    init {
        viewModelScope.launch {
            // Fetch matakuliah
            updateUIState = repositoryMatkul.getMatakuliah(_kode)
                .filterNotNull()
                .first()
                .toUIStateMatkul()

            // Fetch dosen list
            repositoryDosen.getAllDosen().collect { dosenEntities ->
                dosenList = dosenEntities
            }
        }
    }

    // Update state with matakuliah event
    fun updateState(matakuliahEvent: MatakuliahEvent) {
        updateUIState = updateUIState.copy(
            matakuliahEvent = matakuliahEvent
        )
    }

    // Validate form fields
    fun validateFields(): Boolean {
        val event = updateUIState.matakuliahEvent
        val errorState = FormErrorStateMatkul(
            kode = if (event.kode.isNotEmpty()) null else "Kode tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "SKS tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester tidak boleh kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "Jenis tidak boleh kosong",
            dosenpengampu = if (event.dosenpengampu.isNotEmpty()) null else "Dosen Pengampu tidak boleh kosong",
        )
        updateUIState = updateUIState.copy(isentryValid = errorState)
        return errorState.isValidMatkul()
    }

    // Update data in repository
    fun updateData() {
        val currentEvent = updateUIState.matakuliahEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMatkul.updateMatakuliah(currentEvent.toMatakuliahEntity())
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data berhasil diupdate",
                        matakuliahEvent = MatakuliahEvent(),
                        isentryValid = FormErrorStateMatkul()
                    )
                } catch (e: Exception) {
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

    // Reset snackBarMessage
    fun resetSnackBarMessage() {
        updateUIState = updateUIState.copy(snackBarMessage = null)
    }
}

fun Matakuliah.toUIStateMatkul(): MtkUIState = MtkUIState(
    matakuliahEvent = this.toDetailUiEvent()
)
