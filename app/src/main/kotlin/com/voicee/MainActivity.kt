package com.voicee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.voicee.core.designsystem.theme.VoiceeTheme
import com.voicee.core.model.DeviceProfile
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VoiceeTheme {
                val context = LocalContext.current
                val profile = remember { readDeviceProfile(context) }
                HomeScreen(profile)
            }
        }
    }
}

@Composable
private fun HomeScreen(profile: DeviceProfile) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(stringResource(R.string.app_name), style = MaterialTheme.typography.headlineLarge)
            Text(stringResource(R.string.phase_zero_status), style = MaterialTheme.typography.bodyLarge)
            DeviceCard(profile)
        }
    }
}

@Composable
private fun DeviceCard(profile: DeviceProfile) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(stringResource(R.string.device_heading), style = MaterialTheme.typography.titleMedium)
            Text("${profile.manufacturer} ${profile.model}")
            Text("Android ${profile.androidVersion} (API ${profile.sdkInt})")
            Text("RAM: ${profile.totalRamLabel} · CPU cores: ${profile.cpuCores}")
            Text("ABIs: ${profile.supportedAbis.joinToString()}")
            Text(
                if (profile.supportsArm64) "64-bit ARM: ready for neural voices"
                else "64-bit ARM not available: neural voices will not run",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    VoiceeTheme(dynamicColor = false) {
        HomeScreen(
            DeviceProfile("OPPO", "F29", "15", 35, listOf("arm64-v8a"), 8L shl 30, 8),
        )
    }
}
