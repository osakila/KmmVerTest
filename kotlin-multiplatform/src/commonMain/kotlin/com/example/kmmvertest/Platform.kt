package com.example.kmmvertest

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform