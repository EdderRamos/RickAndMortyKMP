package com.edlabcode.rickmortyapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform