package com.example.ucp2.ui.viewmatakuliah

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.UpdateMatakuliahViewModel
import kotlinx.coroutines.launch


@Composable
fun UpdateMtkView(
  onBack: () -> Unit,
  onNavigate: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: UpdateMatakuliahViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
  val uiState = viewModel.updateUIState
  val snackbarHostState = remember { SnackbarHostState() }
  val corutineScope = rememberCoroutineScope()

  LaunchedEffect(uiState.snackBarMessage) {
    println("LaunchedEffect triggered")
    uiState.snackBarMessage?.let { message ->
      println("Snackbar message received: $message")
      corutineScope.launch {
        println("Launching coroutine for snackbar")
        snackbarHostState.showSnackbar(
          message = message,
          duration = SnackbarDuration.Long
        )
        viewModel.resetSnackBarMessage()
      }
    }

    Scaffold (
      modifier = modifier,
      snackbarHost = {SnackbarHostState(hostState = snackbarHostState) },
      topBar = {
        TopAppBar(
          judul = "Edit Matakuliah",
          showBackButton = true,
          onBack = onBack,
        )
      }
    )


  }
}