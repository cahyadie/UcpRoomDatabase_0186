package com.example.ucp2

import android.app.Application
import com.example.praktikum9.Depedenciesinjection.ContainerApp

class SaveApp: Application()  {
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this)
    }
}
