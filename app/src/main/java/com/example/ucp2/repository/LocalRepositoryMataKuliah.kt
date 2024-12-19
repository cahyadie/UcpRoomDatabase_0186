package com.example.praktikum9.repository

import com.example.ucp2.Data.dao.MataKuliahDao
import com.example.ucp2.Data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryMataKuliah

import kotlinx.coroutines.flow.Flow

class LocalRepositoryMataKuliah(
    private val mataKuliahDao: MataKuliahDao

) : RepositoryMataKuliah {
    override suspend fun insertMataKuliah(mataKuliah: MataKuliah)  {
        mataKuliahDao.insertMataKuliah(mataKuliah)
    }

    override fun getAllMataKuliah(): Flow<List<MataKuliah>> {
        return mataKuliahDao.getAllMataKuliah()
    }

    override fun getMataKuliah(kode: String): Flow<MataKuliah> {
        return mataKuliahDao.getMataKuliah(kode)
    }

    override suspend fun deleteMataKuliah(mataKuliah: MataKuliah) {
        mataKuliahDao.deleteMataKuliah(mataKuliah)
    }

    override suspend fun updateMataKuliah(mataKuliah: MataKuliah) {
        mataKuliahDao.updateMataKuliah(mataKuliah)
    }
}