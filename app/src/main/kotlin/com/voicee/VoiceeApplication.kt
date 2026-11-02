package com.voicee

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/** App entry point. `@HiltAndroidApp` switches on dependency injection for the whole app. */
@HiltAndroidApp
class VoiceeApplication : Application()
