package com.example.ucp2.ui.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ucp2.entity.Matakuliah
import com.example.ucp2.repository.RepositoryMatkul

class MatakuliahViewModel(private val repositoryMatkul: RepositoryMatkul): ViewModel(){

    var uiState by mutableStateOf(MatakuliahEvent())
}


data class MtkUIState(
    val matakuliahEvent: MatakuliahEvent = MatakuliahEvent()
)

data class FormErrorState(
    val kode: String? = null,
    val nama: String? = null,
    val sks: String? = null,
    val semester: String? = null,
    val jenis: String? = null,
    val dosenpengampu: String? = null,

){
    fun isValid(): Boolean {
        return kode == null && nama == null && sks == null &&
                semester == null && jenis == null && dosenpengampu == null
    }
}

data class MatakuliahEvent(
    val kode: String = " ",
    val nama: String = " ",
    val sks: String = " ",
    val semester: String = " ",
    val jenis: String = " ",
    val dosenpenngampu: String = " ",
)
fun MatakuliahEvent.toMatakuliahEntity(): Matakuliah = Matakuliah(
    kode = kode,
    nama = nama,
    sks = sks,
    semester = semester,
    jenis = jenis,
    dosenpengampu = dosenpenngampu
)