package com.example.ucp2.ui.viewmatakuliah

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.viewmodel.FormErrorStateMatkul
import com.example.ucp2.ui.viewmodel.MatakuliahEvent
import com.example.ucp2.ui.viewmodel.MatakuliahViewModel
import com.example.ucp2.ui.viewmodel.PenyediaViewModel

@Composable
fun InsertMatakuliahView(
  onBack: () -> Unit,
  onNavigate: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: MatakuliahViewModel = viewModel(factory = PenyediaViewModel.Factory)
){}

@Composable
fun FormMatakuliah(
  matakuliahEvent: MatakuliahEvent = MatakuliahEvent(),
  onValueChange: (MatakuliahEvent) -> Unit,
  errorState: FormErrorStateMatkul = FormErrorStateMatkul(),
  modifier: Modifier = Modifier
){
    val semester = listOf("1","2","3","4","5","6","7","8")
    val sks = listOf("1","2","3")

    Column (
        modifier = modifier.fillMaxWidth()
    ){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.nama,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(nama = it))
            },
            label = { Text("nama")},
            isError = errorState.nama !=null,
            placeholder = { Text("Masukkan nama")}
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.kode,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(kode = it))
            },
            label = { Text("kode")},
            isError = errorState.kode !=null,
            placeholder = { Text("Masukkan kode")}
        )
        Text(
            text = errorState.kode ?: "",
            color = Color.Red
        )
    }

}