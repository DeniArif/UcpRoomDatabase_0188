package com.example.ucp2.ui.viewmatakuliah

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.cotumwidget.TopAppBar
import com.example.ucp2.ui.navigation.AlamatNavigasi
import com.example.ucp2.ui.viewmodel.FormErrorStateMatkul
import com.example.ucp2.ui.viewmodel.MatakuliahEvent
import com.example.ucp2.ui.viewmodel.MatakuliahViewModel
import com.example.ucp2.ui.viewmodel.MtkUIState
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

object DestinasiInsert : AlamatNavigasi{
    override val route: String = "Insert_mtk"
}

@Composable
fun InsertMatakuliahView(
  onBack: () -> Unit,
  onNavigate: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: MatakuliahViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.reserSnackBarMessage()
            }
        }
    }
    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ){
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Matakuliah"
            )

            InesrtBodyMatkul(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateState(updateEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveData()
                    }
                    onNavigate
                }
            )
        }
    }

}

@Composable
fun InesrtBodyMatkul(
    modifier: Modifier = Modifier,
    onValueChange: (MatakuliahEvent) -> Unit,
    uiState: MtkUIState,
    onClick: () -> Unit
){
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        FormMatakuliah(
            matakuliahEvent = uiState.matakuliahEvent,
            onValueChange = onValueChange,
            errorState = uiState.isentryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Simpan")
        }
    }
}

@Composable
fun FormMatakuliah(
  matakuliahEvent: MatakuliahEvent = MatakuliahEvent(),
  onValueChange: (MatakuliahEvent) -> Unit,
  errorState: FormErrorStateMatkul = FormErrorStateMatkul(),
  modifier: Modifier = Modifier
){
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

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "SKS")
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            sks.forEach{ sks ->
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    RadioButton(
                        selected = matakuliahEvent.sks == sks,
                        onClick =  {
                            onValueChange(matakuliahEvent.copy(sks = sks))
                        },
                    )
                    Text(
                        text = sks,
                    )
                }
            }
        }
        Text(
            text = errorState.sks ?:" ",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.semester,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(semester = it))
            },
            label = { Text("Semester")},
            isError = errorState.jenis !=null,
            placeholder = { Text("Masukkan Semester")}
        )
        Text(
            text = errorState.jenis ?: "",
            color = Color.Red
        )



        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.jenis,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(jenis = it))
            },
            label = { Text("Jenis")},
            isError = errorState.jenis !=null,
            placeholder = { Text("Masukkan Jenis")}
        )
        Text(
            text = errorState.jenis ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.dosenpengampu,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(dosenpengampu = it))
            },
            label = { Text("Dosen Pengampu")},
            isError = errorState.nama !=null,
            placeholder = { Text("Masukkan Dosen Pengampu")}
        )
        Text(
            text = errorState.dosenpengampu ?: "",
            color = Color.Red
        )
    }

}