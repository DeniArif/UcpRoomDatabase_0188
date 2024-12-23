package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Matakuliah
import com.example.ucp2.repository.RepositoryMatkul
import com.example.ucp2.ui.navigation.DestinasiDetail
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailMatakuliahViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMatkul: RepositoryMatkul,

    ) : ViewModel(){
        private val _kode: String = checkNotNull(savedStateHandle[DestinasiDetail.KODE])
            val detailUiState: StateFlow<DetailUiState> = repositoryMatkul.getMatakuliah(_kode)
                .filterNotNull()
                .map {
                    DetailUiState(
                        detailUiEvent = it.toDetailUiEvent(),
                        isLoading = false,
                    )
                }
                .onStart {
                    emit(DetailUiState(isLoading = true))
                    delay(600)
                }
                .catch {
                    emit(
                        DetailUiState(
                            isLoading = false,
                            isError = true,
                            errorMessage = it.message ?: " Terjadi Kesalahan",
                        )
                    )
                }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(2000),
                    initialValue = DetailUiState(
                        isLoading = true,
                    ),
                )

    fun deleteMatkul() {
        detailUiState.value.detailUiEvent.toMatakuliahEntity().let {
            viewModelScope.launch {
                repositoryMatkul.deleteMatakuliah(it)
            }
        }
    }
}

data class DetailUiState(
    val detailUiEvent: MatakuliahEvent = MatakuliahEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = " "
){
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == MatakuliahEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != MatakuliahEvent()
}

fun Matakuliah.toDetailUiEvent() : MatakuliahEvent {
    return  MatakuliahEvent(
        kode = kode,
        nama = nama,
        sks = sks,
        semester = semester,
        jenis = jenis,
        dosenpengampu = dosenpengampu
    )
}