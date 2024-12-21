package com.example.ucp2.ui.Navigation

interface Navigasi {
    val route: String

    object DestinasiHome : Navigasi {
        override val route = "home"
    }

    object DestinasiDetail : Navigasi {
        override val route = "detail"
        const val Kode = "kode"
        const val Nidn = "nidn"
        val routeWithArg = "$route/{$Kode}"
    }

    object DestinasiUpdate : Navigasi {
        override val route = "update"
        const val Nidn = "nidn"
        const val Kode = "kode"
        val routeWithArg = "$route/{$Nidn}/{$Kode}"
    }
}