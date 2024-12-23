package com.example.ucp2.ui.navigation

interface AlamatNavigasi{
    val route : String
}

object DestinasiHomeDosen : AlamatNavigasi{
    override val route = " home_dosen "
}

object DestinasiHomeMatkul : AlamatNavigasi{
    override val route = " home_matkul "
}


object DestinasiDetail : AlamatNavigasi{
    override  val route = "detail_matkul"
    const val KODE = "kode"
    val routesWithArg = "$route/{$KODE}"
}

object DestinasiUpdateMatkul : AlamatNavigasi{
    override  val route = "update_matakul"
    const val KODE = "kode"
    val routeWithArg = "$route/{$KODE}"
}
