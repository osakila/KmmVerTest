package com.example.kmmvertest

import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = "1.0.1 " + UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()