package com.example.ucp2.repository

import com.example.ucp2.data.dao.DosenDao
import com.example.ucp2.data.dao.MatakuliahDao
import com.example.ucp2.entity.Dosen
import com.example.ucp2.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

class LocalRepositoryMatkul (
    private val matakuliahDao: MatakuliahDao
): RepositoryMatkul {

    override suspend fun insertMatakuliah(matakuliah: Matakuliah) {
        matakuliahDao.insertMatakuliah(matakuliah)
    }

    override fun getAllMatakuliah(): Flow<List<Dosen>> {
        return matakuliahDao.getAllMatakuliah()
    }
}