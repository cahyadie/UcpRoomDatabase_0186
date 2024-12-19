package com.example.praktikum9.repository

import com.example.ucp2.Data.dao.DosenDao
import com.example.ucp2.Data.entity.Dosen
import com.example.ucp2.Data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryMataKuliah

import kotlinx.coroutines.flow.Flow

class LocalRepositoryDosen(
    private val dosenDao: DosenDao,

) : RepositoryDosen{
    override suspend fun insertDosen(dosen: Dosen) {
        dosenDao.insertDosen(dosen)
    }

    override fun getAllDosen(): Flow<List<Dosen>> {
        return dosenDao.getAllDosen()
    }

    override fun getDosen(nidn: String): Flow<Dosen> {
        return dosenDao.getDosen(nidn)
    }
}
