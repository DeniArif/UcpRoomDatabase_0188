package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.ucp2.entity.Dosen
import com.example.ucp2.repository.RepositoryDosen

class DetailDosenViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDosen: RepositoryDosen,
): ViewModel(){
    private val _nidn: String = checkNotNull(savedStateHandle[DetailDosenDetail.NIDN])
}

data class DetailDosenViewModel(
    val detailUiEvent: DosenEvent = DosenEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = " "
) {
    val isiUiEventEmpty: Boolean
        get() = detailUiEvent == DosenEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != DosenEvent()
}

fun Dosen.toDetailUiEvent() : DosenEvent {
    return  DosenEvent(
        nidn = nidn,
        nama = nama,
        jeniskelamin = jeniskelamin
    )
}