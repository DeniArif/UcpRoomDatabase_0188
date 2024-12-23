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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.ui.cotumwidget.TopAppBar
import com.example.ucp2.ui.navigation.AlamatNavigasi
import com.example.ucp2.ui.viewmodel.FormErrorStateMatkul
import com.example.ucp2.ui.viewmodel.MatakuliahEvent
import com.example.ucp2.ui.viewmodel.MatakuliahViewModel
import com.example.ucp2.ui.viewmodel.MtkUIState
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

object DestinasiInsert : AlamatNavigasi {
    override val route: String = "Insert_mtk"
}

@Composable
fun InsertMatakuliahView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MatakuliahViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState
    val dosenList = viewModel.dosenList // Ambil daftar dosen dari viewModel
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Menangani pesan snackbar
    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Matakuliah"
            )

            InesrtBodyMatkul(
                uiState = uiState,
                onValueChange = { updateEvent -> viewModel.updateState(updateEvent) },
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveData()
                    }
                    onNavigate()
                },
                dosenList = uiState.dosenList  // Kirim daftar dosen ke FormMatakuliah
            )
        }
    }
}


@Composable
fun InesrtBodyMatkul(
    modifier: Modifier = Modifier,
    onValueChange: (MatakuliahEvent) -> Unit,
    uiState: MtkUIState,
    onClick: () -> Unit,
    dosenList: List<Dosen>
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormMatakuliah(
            matakuliahEvent = uiState.matakuliahEvent,
            onValueChange = onValueChange,
            errorState = uiState.isentryValid,
            modifier = Modifier.fillMaxWidth(),
            dosenList = dosenList
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormMatakuliah(
    matakuliahEvent: MatakuliahEvent = MatakuliahEvent(),
    onValueChange: (MatakuliahEvent) -> Unit,
    errorState: FormErrorStateMatkul = FormErrorStateMatkul(),
    modifier: Modifier = Modifier,
    dosenList: List<Dosen> = emptyList()  // Tambahkan dosenList sebagai parameter
) {
    val sks = listOf("1", "2", "3")
    val jenis = listOf("Wajib", "Peminatan")
    val expanded = remember { mutableStateOf(false) }
    val selectedDosen = rememberSaveable { mutableStateOf(matakuliahEvent.dosenpengampu) }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.nama,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(nama = it))
            },
            label = { Text("Nama") },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan nama") }
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
            label = { Text("Kode") },
            isError = errorState.kode != null,
            placeholder = { Text("Masukkan kode") }
        )
        Text(
            text = errorState.kode ?: "",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "SKS")
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            sks.forEach { sks ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = matakuliahEvent.sks == sks,
                        onClick = {
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
            text = errorState.sks ?: "",
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.semester,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(semester = it))
            },
            label = { Text("SEMESTER") },
            isError = errorState.semester != null,
            placeholder = {Text("Masukkan Semester")},

            )
        Text(
            text = errorState.semester ?:"",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Jenis MataKuliah")
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            jenis.forEach{ jm ->
                Row  (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    RadioButton(
                        selected = matakuliahEvent.jenis == jm,
                        onClick = {
                            onValueChange(matakuliahEvent.copy(jenis = jm))
                        }
                    )

                    Text(
                        text = jm,
                    )
                }
            }
        }
        Text(
            text = errorState.jenis?: "",
            color = Color.Red
        )


        ExposedDropdownMenuBox(
            expanded = expanded.value,
            onExpandedChange = { expanded.value = !expanded.value }
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                value = selectedDosen.value,
                onValueChange = {},
                readOnly = true,
                label = { Text("Dosen Pengampu") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Gray
                )
            )

            ExposedDropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false }
            ) {
                dosenList.forEach { dosen ->
                    androidx.compose.material3.DropdownMenuItem(
                        text = { Text(dosen.nama) }, // Gunakan properti nama dari objek Dosen
                        onClick = {
                            selectedDosen.value = dosen.nama // Set nilai dosen yang dipilih
                            onValueChange(matakuliahEvent.copy(dosenpengampu = dosen.nama))
                            expanded.value = false
                        }
                    )
                }
            }

        }

        Text(
            text = errorState.dosenpengampu ?: "",
            color = Color.Red
        )
    }
}
