package com.example.ucp2.Data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2.Data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

@Dao
interface MataKuliahDao {
    @Query("select * From MataKuliah ORDER BY nama ASC")
    fun getAllMataKuliah() : Flow<List<MataKuliah>>

    @Query("SELECT * FROM MataKuliah WHERE kode = kode = :kode")
    fun getMataKuliah(kode: String): Flow<MataKuliah>

    @Delete
    suspend fun deleteMataKuliah(mataKuliah: MataKuliah)

    @Update
    suspend fun updateMataKuliah(mataKuliah: MataKuliah)

    @Insert
    suspend fun insertMataKuliah(mataKuliah: MataKuliah)
}