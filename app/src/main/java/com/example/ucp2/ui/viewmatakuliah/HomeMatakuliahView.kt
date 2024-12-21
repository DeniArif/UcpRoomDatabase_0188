package com.example.ucp2.ui.viewmatakuliah

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    onClick: (String) -> Unit = { },
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