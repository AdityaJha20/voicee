package com.voicee.core.model

import java.util.Locale

/**
 * The hardware facts that decide which voice models are realistic on a phone.
 * The voice test bench records this alongside every measurement.
 */
data class DeviceProfile(
    val manufacturer: String,
    val model: String,
    val androidVersion: String,
    val sdkInt: Int,
    val supportedAbis: List<String>,
    val totalRamBytes: Long,
    val cpuCores: Int,
) {
    /** True when the phone can run 64-bit ARM native code, which sherpa-onnx needs. */
    val supportsArm64: Boolean get() = "arm64-v8a" in supportedAbis

    /** Total RAM rounded to one decimal place, e.g. "7.4 GB". */
    val totalRamLabel: String get() = formatGigabytes(totalRamBytes)
}

internal fun formatGigabytes(bytes: Long): String {
    val gb = bytes / (1024.0 * 1024.0 * 1024.0)
    return String.format(Locale.US, "%.1f GB", gb)
}
