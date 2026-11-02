import com.android.build.api.dsl.CommonExtension
import com.voicee.buildlogic.libs
import com.voicee.buildlogic.library
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/** Turns on Jetpack Compose for an Android module that already applies an Android plugin. */
class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

        extensions.getByType(CommonExtension::class.java).buildFeatures.compose = true

        dependencies {
            val bom = platform(libs.library("androidx-compose-bom"))
            add("implementation", bom)
            add("androidTestImplementation", bom)
            add("implementation", libs.library("androidx-compose-ui"))
            add("implementation", libs.library("androidx-compose-ui-graphics"))
            add("implementation", libs.library("androidx-compose-ui-tooling-preview"))
            add("implementation", libs.library("androidx-compose-material3"))
            add("debugImplementation", libs.library("androidx-compose-ui-tooling"))
        }
    }
}
