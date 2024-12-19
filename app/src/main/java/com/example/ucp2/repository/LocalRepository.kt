package com.example.praktikum9.repository

import com.example.ucp2.Data.dao.DosenDao
import com.example.ucp2.Data.dao.MataKuliahDao
import com.example.ucp2.Data.entity.Dosen
import com.example.ucp2.Data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryMataKuliah

import kotlinx.coroutines.flow.Flow

class LocalRepository(
    private val dosenDao: DosenDao,
    private val mataKuliahDao: MataKuliahDao

) : RepositoryDosen,
    RepositoryMataKuliah {
    override suspend fun insertDosen(dosen: Dosen) {
        dosenDao.insertDosen(dosen)
    }

    override fun getAllDosen(): Flow<List<Dosen>> {
        return dosenDao.getAllDosen()
    }

    override fun getDosen(nidn: String): Flow<Dosen> {
        return dosenDao.getDosen(nidn)
    }

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
