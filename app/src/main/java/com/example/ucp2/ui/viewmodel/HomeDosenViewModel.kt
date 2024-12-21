package com.example.ucp2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ucp2.repository.RepositoryDosen

class DosenViewModel(private val  repositoryDosen: RepositoryDosen) : ViewModel(){

    var uiState by mutableStateOf(DOsenUIState())
}

data class DOsenUIState(
    val dosenEvent: DosenEvent = DosenEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null
)

data class FormErrorState(
    val nidn: String? = null,
    val nama: String? = null,
    val jeniskelamin: String? = null
) {
    fun isValid(): Boolean {
        return nidn == null && nama == null &&
                jeniskelamin == null
    }
}

data class DosenEvent(
    val nidn: String = " ",
    val nama: String = " ",
    val jeniskelamin: String = " "
)