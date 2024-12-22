package com.example.ucp2.ui.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.entity.Matakuliah
import com.example.ucp2.repository.RepositoryMatkul
import kotlinx.coroutines.launch

class MatakuliahViewModel(private val repositoryMatkul: RepositoryMatkul): ViewModel(){

    var uiState by mutableStateOf(MtkUIState())

    fun updateState(matakuliahEvent: MatakuliahEvent) {
        uiState = uiState.copy(
            matakuliahEvent = matakuliahEvent
        )
    }

    private fun validateFields(): Boolean {
        val event = uiState.matakuliahEvent
        val errorState = FormErrorStateMatkul(
            kode = if (event.kode.isNotEmpty()) null else "Kode tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "SKS tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester tidak boleh kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "Jenis tidak boleh kosong",
            dosenpengampu = if (event.dosenpengampu.isNotEmpty()) null else "Dosen Pengampu tidak boleh kosong"
        )
        uiState = uiState.copy(isentryValid = errorState)
        return errorState.isValidMatkul()
    }

    fun saveData() {
        val currentEvent = uiState.matakuliahEvent
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMatkul.insertMatakuliah(currentEvent.toMatakuliahEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        matakuliahEvent = MatakuliahEvent(),
                        isentryValid = FormErrorStateMatkul()
                    )
                } catch (e: Exception){
                    uiState = uiState.copy(
                        snackBarMessage = "Input tidak valid. Periksa kembali data anda"
                    )
                }
            }
        }
    }
    fun reserSnackBarMessage() {
        uiState =uiState.copy(snackBarMessage = null)
    }
}



data class MtkUIState(
    val matakuliahEvent: MatakuliahEvent = MatakuliahEvent(),
    val isentryValid: FormErrorStateMatkul = FormErrorStateMatkul(),
    val snackBarMessage: String? = null
)

data class FormErrorStateMatkul(
    val kode: String? = null,
    val nama: String? = null,
    val sks: String? = null,
    val semester: String? = null,
    val jenis: String? = null,
    val dosenpengampu: String? = null,

){
    fun isValidMatkul(): Boolean {
        return kode == null && nama == null && sks == null &&
                semester == null && jenis == null && dosenpengampu == null
    }
}

data class MatakuliahEvent(
    val kode: String = "",
    val nama: String = "",
    val sks: String = "",
    val semester: String = "",
    val jenis: String = "",
    val dosenpengampu: String = "",
)

fun MatakuliahEvent.toMatakuliahEntity(): Matakuliah = Matakuliah(
    kode = kode,
    nama = nama,
    sks = sks,
    semester = semester,
    jenis = jenis,
    dosenpengampu = dosenpengampu
)