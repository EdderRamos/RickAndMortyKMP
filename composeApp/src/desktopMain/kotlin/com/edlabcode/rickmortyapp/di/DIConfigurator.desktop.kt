package com.edlabcode.rickmortyapp.di

import com.edlabcode.rickmortyapp.data.database.getDataBase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module {
    return module {
        single { getDataBase() }
    }
}