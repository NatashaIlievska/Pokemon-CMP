package com.natasha.pockemon

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val type = PlatformType.ANDROID
}

actual fun getPlatform(): Platform = AndroidPlatform()