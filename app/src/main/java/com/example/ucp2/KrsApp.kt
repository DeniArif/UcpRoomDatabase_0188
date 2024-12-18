package com.example.ucp2

import android.app.Application
import com.example.ucp2.dependeciesinjection.ContainerApp
import com.example.ucp2.dependeciesinjection.InterfaceContainerApp

class KrsApp : Application() {
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this)
    }
}