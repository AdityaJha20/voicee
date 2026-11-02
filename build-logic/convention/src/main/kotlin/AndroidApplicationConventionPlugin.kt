import com.android.build.api.dsl.ApplicationExtension
import com.voicee.buildlogic.configureAndroidCommon
import com.voicee.buildlogic.libs
import com.voicee.buildlogic.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/** Configures the single phone app module. Kotlin support is built into AGP 9. */
class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.android.application")

        extensions.configure<ApplicationExtension> {
            configureAndroidCommon(this)
            defaultConfig.targetSdk = libs.version("targetSdk").toInt()
        }
    }
}
