package com.edlabcode.rickmortyapp.di

import com.edlabcode.rickmortyapp.domain.GetRandomCharacter
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetRandomCharacter)

}