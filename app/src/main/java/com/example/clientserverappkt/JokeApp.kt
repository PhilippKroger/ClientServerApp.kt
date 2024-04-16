package com.example.clientserverappkt

import android.app.Application

class JokeApp: Application() {
    lateinit var viewModel: MainViewModel
    override fun onCreate() {
        super.onCreate()
        viewModel = MainViewModel(
            FakeModel()
        )
    }
}