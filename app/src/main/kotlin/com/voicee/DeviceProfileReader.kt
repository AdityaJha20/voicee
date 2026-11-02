package com.voicee

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import com.voicee.core.model.DeviceProfile

/** Reads the phone's hardware facts from Android system APIs. */
fun readDeviceProfile(context: Context): DeviceProfile {
    val memoryInfo = ActivityManager.MemoryInfo()
    context.getSystemService(ActivityManager::class.java).getMemoryInfo(memoryInfo)

    return DeviceProfile(
        manufacturer = Build.MANUFACTURER,
        model = Build.MODEL,
        androidVersion = Build.VERSION.RELEASE,
        sdkInt = Build.VERSION.SDK_INT,
        supportedAbis = Build.SUPPORTED_ABIS.toList(),
        totalRamBytes = memoryInfo.totalMem,
        cpuCores = Runtime.getRuntime().availableProcessors(),
    )
}
