package com.shahin.mlkit.di

import com.shahin.mlkit.ui.fragments.camera.CameraViewModel
import com.shahin.mlkit.ui.fragments.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { CameraViewModel(get()) }
}