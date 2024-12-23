package com.example.ucp2.repository

import com.example.ucp2.data.dao.DosenDao
import com.example.ucp2.data.dao.MatakuliahDao
import com.example.ucp2.data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

class LocalRepositoryMatkul (
    private val matakuliahDao: MatakuliahDao
): RepositoryMatkul {

    override suspend fun insertMatakuliah(matakuliah: Matakuliah) {
        matakuliahDao.insertMatakuliah(matakuliah)
    }

    override fun getAllMatakuliah(): Flow<List<Matakuliah>> {
        return matakuliahDao.getAllMatakuliah()
    }

    override fun getMatakuliah(id: String): Flow<Matakuliah> {
        return matakuliahDao.getMatakuliah(id)
    }

    override suspend fun deleteMatakuliah(matakuliah: Matakuliah) {
        matakuliahDao.deleteMatakuliah(matakuliah)
    }

    override suspend fun updateMatakuliah(matakuliah: Matakuliah) {
        matakuliahDao.updateMatakuliah(matakuliah)
    }

}