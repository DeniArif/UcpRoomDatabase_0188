package com.example.ucp2.dependeciesinjection

import android.content.Context
import com.example.ucp2.database.KrsDatabaseDosen
import com.example.ucp2.repository.LocalRepositoryDosen
import com.example.ucp2.repository.RepositoryDosen
import com.example.ucp2.repository.RepositoryMatkul

interface InterfaceContainerApp {
    val repositoryDosen: RepositoryDosen
    val repositoryMatkul:RepositoryMatkul
}

class ContainerApp (private val context: Context) : InterfaceContainerApp {
    override  val repositoryDosen: RepositoryDosen by lazy {
        LocalRepositoryDosen(KrsDatabaseDosen.getDatabase(context).dosen())
    }
}
