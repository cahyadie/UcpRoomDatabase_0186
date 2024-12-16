package com.example.praktikum9.repository

import com.example.ucp2.Data.entity.Dosen
import kotlinx.coroutines.flow.Flow

interface RepositoryDosen {
    suspend fun insertDosen(Dosen: Dosen)
    fun getAllMahasiswa(): Flow<List<Dosen>>

    fun getMhs(nim: String): Flow<Dosen>
    fun getAllDosen(): Flow<List<Dosen>>
    fun getDosen(nidn: String): Flow<Dosen>
}