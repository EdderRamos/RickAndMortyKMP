package com.edlabcode.rickmortyapp.di

import com.edlabcode.rickmortyapp.data.database.RickMortyDatabase
import com.edlabcode.rickmortyapp.data.database.getDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module {
    return module {
        single<RickMortyDatabase> { getDatabase() }
    }
}