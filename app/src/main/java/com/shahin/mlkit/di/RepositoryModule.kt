package com.shahin.mlkit.di

import com.shahin.mlkit.data.sources.local.LocalRepository
import com.shahin.mlkit.data.sources.local.LocalRepositoryImpl
import com.shahin.mlkit.data.sources.mlkit.MlkitRepository
import com.shahin.mlkit.data.sources.mlkit.MlkitRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<LocalRepository> { LocalRepositoryImpl() }
    factory<MlkitRepository> { MlkitRepositoryImpl() }
}