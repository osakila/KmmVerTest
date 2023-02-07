package com.example.kmmvertest

class IOSPlatform: Platform {
    override val name: String = "js system"
}

actual fun getPlatform(): Platform = IOSPlatform()