package com.voicee.core.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DeviceProfileTest {

    private fun profile(abis: List<String>, ram: Long = 8L * 1024 * 1024 * 1024) = DeviceProfile(
        manufacturer = "OPPO",
        model = "F29",
        androidVersion = "15",
        sdkInt = 35,
        supportedAbis = abis,
        totalRamBytes = ram,
        cpuCores = 8,
    )

    @Test
    fun `arm64 phones are detected`() {
        assertTrue(profile(listOf("arm64-v8a", "armeabi-v7a")).supportsArm64)
    }

    @Test
    fun `32-bit only phones are not arm64`() {
        assertFalse(profile(listOf("armeabi-v7a")).supportsArm64)
    }

    @Test
    fun `ram is shown in gigabytes with one decimal`() {
        assertEquals("8.0 GB", profile(listOf("arm64-v8a")).totalRamLabel)
        assertEquals("7.4 GB", formatGigabytes(7_946_000_000L))
    }
}
