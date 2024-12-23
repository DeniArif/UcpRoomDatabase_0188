package com.example.ucp2.repository

import com.example.ucp2.data.dao.MatakuliahDao
import com.example.ucp2.data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

interface RepositoryMatkul {
    suspend fun insertMatakuliah(matkul: Matakuliah)

    fun getAllMatakuliah(): Flow<List<Matakuliah>>

    fun getMatakuliah(kode: String): Flow<Matakuliah>

    suspend fun deleteMatakuliah(matkul: Matakuliah)

    suspend fun updateMatakuliah(matakuliah: Matakuliah)
}