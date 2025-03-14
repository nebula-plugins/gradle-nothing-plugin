package nebula.plugin.nothing

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

class MaybeNothingSettingsPlugin implements Plugin<Settings> {
    @Override
    void apply(Settings settings) {
        settings.gradle.rootProject {rootProject ->
            def dependencyHandler = rootProject.buildscript.dependencies
            def buildClasspathDependencies = ['com.netflix.nebula:nebula-project-plugin:11.0.0']
            buildClasspathDependencies.each {
                dependencyHandler.add("classpath", it)
            }
        }
    }
}
