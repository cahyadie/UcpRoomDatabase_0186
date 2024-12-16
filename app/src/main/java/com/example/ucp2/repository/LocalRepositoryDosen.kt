package com.example.praktikum9.repository

import com.example.ucp2.Data.dao.DosenDao
import com.example.ucp2.Data.entity.Dosen

import kotlinx.coroutines.flow.Flow

class LocalRepositoryDosen(
    private val DosenDao: DosenDao
) : RepositoryDosen {
    override suspend fun insertDosen(Dosen: Dosen) {
        DosenDao.insertDosen(Dosen)
    }

    override fun getAllDosen(): Flow<List<Dosen>> {
        return DosenDao.getAllDosen()
    }

    override fun getDosen(nidn: String): Flow<Dosen> {
        return DosenDao.getDosen(nidn)
    }
}
