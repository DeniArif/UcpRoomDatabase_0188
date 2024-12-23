package com.example.ucp2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.data.entity.Matakuliah
import com.example.ucp2.repository.RepositoryDosen
import com.example.ucp2.repository.RepositoryMatkul
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MatakuliahViewModel(
    private val repositoryMatkul: RepositoryMatkul,
    private val repositoryDosen: RepositoryDosen
) : ViewModel() {

    var uiStateMk by mutableStateOf(MtkUIState())
    var dosenList by mutableStateOf<List<String>>(emptyList())  // Menyimpan daftar dosen

    init {
        loadDosenList()
    }

    // Fungsi untuk memperbarui state
    fun updateState(matakuliahEvent: MatakuliahEvent) {
        uiStateMk = uiStateMk.copy(
            matakuliahEvent = matakuliahEvent
        )
    }

    // Validasi input form
    private fun validateFields(): Boolean {
        val event = uiStateMk.matakuliahEvent
        val errorState = FormErrorStateMatkul(
            kode = if (event.kode.isNotEmpty()) null else "Kode tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "SKS tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester tidak boleh kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "Jenis tidak boleh kosong",
            dosenpengampu = if (event.dosenpengampu.isNotEmpty()) null else "Dosen Pengampu tidak boleh kosong"
        )
        uiStateMk = uiStateMk.copy(isentryValid = errorState)
        return errorState.isValidMatkul()
    }

    // Fungsi untuk menyimpan data
    fun saveData() {
        val currentEvent = uiStateMk.matakuliahEvent
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMatkul.insertMatakuliah(currentEvent.toMatakuliahEntity())
                    uiStateMk = uiStateMk.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        matakuliahEvent = MatakuliahEvent(),
                        isentryValid = FormErrorStateMatkul()
                    )
                } catch (e: Exception) {
                    uiStateMk = uiStateMk.copy(
                        snackBarMessage = "Input tidak valid. Periksa kembali data anda"
                    )
                }
            }
        }
    }

    // Fungsi untuk mengatur ulang pesan Snackbar
    fun reserSnackBarMessage() {
        uiStateMk = uiStateMk.copy(snackBarMessage = null)
    }

    // Fungsi untuk memuat daftar dosen
    private fun loadDosenList() {
        viewModelScope.launch {
            repositoryDosen.getAllDosen() // Menggunakan Flow untuk mendapatkan daftar dosen
                .onStart {
                    uiStateMk = uiStateMk.copy(isLoading = true)
                }
                .catch { e ->
                    uiStateMk = uiStateMk.copy(
                        snackBarMessage = "Gagal memuat daftar dosen: ${e.message}",
                        isLoading = false
                    )
                }
                .collect { dosenList ->
                    // Mengambil nama dosen dari setiap objek Dosen dan menyimpannya dalam List<String>
                    uiStateMk = uiStateMk.copy(
                        dosenList = dosenList,  // Memperbarui dosenList di UI State
                        isLoading = false
                    )
                }
        }
    }

}

data class MtkUIState(
    val matakuliahEvent: MatakuliahEvent = MatakuliahEvent(),
    val isentryValid: FormErrorStateMatkul = FormErrorStateMatkul(),
    val snackBarMessage: String? = null,
    val dosenList: List<Dosen> = emptyList(),
    val isLoading: Boolean = false
)

data class FormErrorStateMatkul(
    val kode: String? = null,
    val nama: String? = null,
    val sks: String? = null,
    val semester: String? = null,
    val jenis: String? = null,
    val dosenpengampu: String? = null
) {
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
    val dosenpengampu: String = ""
)

// Menyimpan input form ke dalam entity
fun MatakuliahEvent.toMatakuliahEntity(): Matakuliah = Matakuliah(
    kode = kode,
    nama = nama,
    sks = sks,
    semester = semester,
    jenis = jenis,
    dosenpengampu = dosenpengampu
)
