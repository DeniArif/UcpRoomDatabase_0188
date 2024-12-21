package com.example.ucp2.ui.viewmatakuliah

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.entity.Matakuliah
import com.example.ucp2.repository.RepositoryMatkul
import com.example.ucp2.ui.viewmodel.HomeMatakuliahViewModel
import com.example.ucp2.ui.viewmodel.PenyediaViewModel

@Composable
fun HomeMatakuliahView(
    viewModel: HomeMatakuliahViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddMatkul: () -> Unit = { },
    onDetailClick: (String) -> Unit = { },
    modifier: Modifier = Modifier

){

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

@Composable
fun CardsMatkul (
    matkul: Matakuliah,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
){
    Card (
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding()
    ){
        Column (
            modifier = Modifier.padding(8.dp)
        ){

        }
    }
}