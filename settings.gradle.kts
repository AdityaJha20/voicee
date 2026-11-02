pluginManagement {
    // Our shared build configuration lives in build-logic/ (see README).
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "voicee"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":core:model")
include(":core:designsystem")
include(":engine:tts-api")
