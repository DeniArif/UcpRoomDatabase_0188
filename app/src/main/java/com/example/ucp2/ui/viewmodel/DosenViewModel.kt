package com.example.ucp2.ui.viewmodel


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
        return  nidn == null && nama = null &&
                jeniskelamin == null
    }
}

data class DosenEvent(
    val nidn: String = " ",
    val nama: String = " ",
    val jeniskelamin: String = " "
)