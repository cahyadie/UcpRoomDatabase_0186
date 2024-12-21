package com.example.ucp2.ui.Navigation

interface Navigasi {
    val route: String

    object DestinasiMenu : Navigasi {
        override val route = "Menu"
    }

    object DestinasiHomeDosen : Navigasi {
        override val route = "home_dosen"
    }

    object DestinasiHomeMataKuliah : Navigasi {
        override val route = "home_mata_kuliah"
    }


    object DestinasiDetailMataKuliah : Navigasi {
        override val route = "detail"
        const val Kode = "kode"
        val routeWithArg = "$route/{$Kode}"
    }

    object DestinasiUpdateMataKuliah : Navigasi {
        override val route = "update"
        const val Kode = "kode"
        val routeWithArg = "$route/{$Kode}"
    }
}