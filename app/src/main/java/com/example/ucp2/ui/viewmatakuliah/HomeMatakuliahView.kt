package com.example.ucp2.ui.viewmatakuliah

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.data.entity.Matakuliah
import com.example.ucp2.repository.RepositoryMatkul
import com.example.ucp2.ui.cotumwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.HomeMatakuliahViewModel
import com.example.ucp2.ui.viewmodel.HomeUiState
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeMatakuliahView(
    viewModel: HomeMatakuliahViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddMatkul: () -> Unit = { },
    onDetailClick: (String) -> Unit = { },
    onBack: () -> Unit = { },
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = {
            TopAppBar(
                judul = "Daftar Matakuliah",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddMatkul,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Matakuliah",
                )
            }
        }
    ) { innerPadding ->
        val homeUiState by viewModel.homeUiState.collectAsState()

        BodyHomeMatkulView(
            homeUiState = homeUiState,
            onClick = {
                onDetailClick(it)
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeMatkulView(
    homeUiState: HomeUiState,
    onClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
){
    val corutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when {
        homeUiState.isLoading-> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }

        homeUiState.isError -> {
            LaunchedEffect(homeUiState.errorMessage) {
                homeUiState.errorMessage?.let{ message ->
                    corutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }

        homeUiState.listMatakuliah.isEmpty() -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Tidak ada data matakuliah.",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        else -> {
            ListMatakuliah(
                listMatkul = homeUiState.listMatakuliah,
                onClick = {
                    onClick(it)
                    println(it)
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListMatakuliah(
    listMatkul: List<Matakuliah>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
){
    LazyColumn(
        modifier = modifier
    ){
        items(
            items = listMatkul,
            itemContent = { matkul ->
                CardsMatkul (
                    matkul = matkul,
                    onClick = { onClick(matkul.kode)}
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsMatkul (
    matkul: Matakuliah,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
){
    Card (
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        Column (
            modifier = Modifier.padding(8.dp)
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
            ){
                Icon(imageVector = Icons.Filled.Person, contentDescription = " ")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = matkul.nama,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment =  Alignment.CenterVertically
            ){
                Icon(imageVector = Icons.Filled.DateRange, contentDescription = " ")
                Spacer(modifier = Modifier.padding(4.dp))

                Text(
                    text = matkul.kode,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}