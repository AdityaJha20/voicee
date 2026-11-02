package com.voicee.engine.tts

/**
 * The contract every text-to-speech engine must follow (Android system TTS,
 * Kokoro, Piper, ...). The rest of the app only talks to this interface, so an
 * engine can be swapped without touching document processing or playback.
 *
 * Phase 0 defines the shape only; implementations arrive in the voice test bench.
 */
interface VoiceEngine {
    /** Stable identifier, e.g. "android-system" or "kokoro-v1.1". */
    val id: String

    /** Voices this engine can speak with right now. */
    suspend fun voices(): List<Voice>

    /** Loads models into memory. Call before [synthesize]; safe to call twice. */
    suspend fun prepare(voice: Voice)

    /** Turns one sentence or paragraph into raw audio. */
    suspend fun synthesize(text: String, voice: Voice): SynthesizedAudio

    /** Frees model memory. The engine must be prepared again before reuse. */
    suspend fun release()
}

data class Voice(
    val id: String,
    val displayName: String,
    val language: VoiceLanguage,
    val gender: VoiceGender,
)

enum class VoiceLanguage { ENGLISH, HINDI }

enum class VoiceGender { FEMALE, MALE, UNSPECIFIED }

/** Mono 16-bit PCM audio, the format every engine is normalised to. */
class SynthesizedAudio(
    val samples: ShortArray,
    val sampleRateHz: Int,
) {
    val durationMillis: Long get() = samples.size * 1000L / sampleRateHz
}
