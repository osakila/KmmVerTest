package com.example.kmmvertest

class AndroidPlatform : Platform {
    override val name: String = "1.0.1 Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()