package com.example.praktikum9.Depedenciesinjection

import android.content.Context
import com.example.praktikum9.repository.LocalRepositoryDosen
import com.example.praktikum9.repository.LocalRepositoryMataKuliah
import com.example.praktikum9.repository.RepositoryDosen
import com.example.ucp2.Data.database.DatabaseAll
import com.example.ucp2.repository.RepositoryMataKuliah

interface InterfaceContainerApp{
    val repositoryDosen: RepositoryDosen
    val repositoryMataKuliah: RepositoryMataKuliah
}


class ContainerApp(private val context: Context) : InterfaceContainerApp {

    override val repositoryDosen: RepositoryDosen by lazy{
        LocalRepositoryDosen(DatabaseAll.getDatabase(context).DosenDao())
    }

    override val repositoryMataKuliah: RepositoryMataKuliah by lazy {
        LocalRepositoryMataKuliah(DatabaseAll.getDatabase(context).MataKuliahDao())
    }
}