plugins {
    alias(libs.plugins.voicee.jvm.library)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.junit)
}
