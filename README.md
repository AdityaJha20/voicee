# Voicee

A personal, offline-first audiobook companion for Android. Import a book, lock the phone, and listen.

**Status:** Phase 0 (project setup) is done. Phase 1, the voice test bench, is next.

## Project layout

| Module | Kind | What it is for |
|---|---|---|
| `app` | Android app | Screens, navigation, and wiring everything together with Hilt |
| `core:model` | Pure Kotlin | Plain data types shared by every module (books, devices, ...) |
| `core:designsystem` | Android + Compose | Theme, colours and reusable UI pieces |
| `engine:tts-api` | Pure Kotlin | The `VoiceEngine` interface that every TTS engine implements |
| `build-logic` | Gradle plugins | Shared build settings, so each module's build file stays tiny |

Dependency versions live in one place: [`gradle/libs.versions.toml`](gradle/libs.versions.toml).

**Rule of thumb:** pure-Kotlin modules must not depend on Android. That keeps business logic fast to build and easy to test.

## Toolchain

| Tool | Version |
|---|---|
| Android Gradle Plugin | 9.5.0, with built-in Kotlin support |
| Kotlin | 2.4.21 |
| Gradle | 9.8.2 (wrapper, checksum-verified) |
| JDK | 21 |
| SDK | compile 37, target 37, min 29 (Android 10) |

## Everyday commands

Run these from the project folder in a terminal.

```bash
./gradlew assembleDebug      # build the app (APK in app/build/outputs/apk/debug/)
./gradlew installDebug       # build and install on the connected phone
./gradlew test               # run unit tests
./gradlew lintDebug          # run Android lint
```

## Claude Code tooling

- `.claude/skills/` holds selected official Android skills from
  [android/skills](https://github.com/android/skills) (Apache-2.0). See `.claude/skills/SOURCE.txt`.
- `.mcp.json` registers the Kotlin documentation server from
  [Kotlin/kotlin-agent-skills](https://github.com/Kotlin/kotlin-agent-skills). It only reads kotlinlang.org.
