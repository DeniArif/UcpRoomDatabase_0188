package com.example.ucp2.ui.viewmatakuliah

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.UpdateMatakuliahViewModel

@Composable
fun UpdateMtkView(
  onBack: () -> Unit,
  onNavigate: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: UpdateMatakuliahViewModel = viewModel(factory = PenyediaViewModel.Factory)
){}