package com.example.ucp2.ui.viewmatakuliah

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.R
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
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {

            // Header Image
            Image(
                painter = painterResource(id = R.drawable.umy),
                contentDescription = "Logo Universitas",
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 16.dp)
            )

            // Welcome Text
            Text(
                text = "Selamat Datang di Aplikasi",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Universitas Muhammadiyah Yogyakarta",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Buttons Section
            ElevatedButton(
                onClick = onNavigateToMatakuliah,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Kelola Matakuliah",
                    tint = Color.White,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = "Kelola Matakuliah", color = Color.White)
            }

            ElevatedButton(
                onClick = onNavigateToDosen,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Kelola Dosen",
                    tint = Color.White,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = "Kelola Dosen", color = Color.White)
            }
        }
    }
}