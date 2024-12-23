package com.example.ucp2.ui.viewmatakuliah

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.ui.cotumwidget.TopAppBar

@Composable
fun HomeView(
    onNavigateToMatakuliah: () -> Unit = {},
    onNavigateToDosen: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                judul = "Beranda",
                showBackButton = false,
                onBack = {},
                modifier = modifier
            )
        }
    ) { innerPadding ->
        HomeContent(
            onNavigateToMatakuliah = onNavigateToMatakuliah,
            onNavigateToDosen = onNavigateToDosen,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun HomeContent(
    onNavigateToMatakuliah: () -> Unit = {},
    onNavigateToDosen: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Selamat Datang di Aplikasi",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Kelola data matakuliah dan dosen dengan mudah.",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Button(
                onClick = onNavigateToMatakuliah,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Matakuliah",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = "Kelola Matakuliah")
            }

            Button(
                onClick = onNavigateToDosen
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Dosen",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = "Kelola Dosen")
            }
        }
    }
}