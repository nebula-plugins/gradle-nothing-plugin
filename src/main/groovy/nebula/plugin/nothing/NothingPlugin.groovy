package nebula.plugin.nothing

import com.google.common.collect.ImmutableList
import org.apache.commons.logging.impl.NoOpLog
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.plugins.JavaPlugin

/**
 * Does nothing.
 */
class NothingPlugin extends NoOpLog implements Plugin<Project> {

    Logger logger = Logging.getLogger(NothingPlugin);

    Project project

    void apply(Project project) {

        this.project = project

        logger.info("Hello, " + ImmutableList.of("friend").get(0));

        project.plugins.withType(JavaPlugin).configureEach {
            try {
                ClassLoader classLoader = NothingPlugin.class.getClassLoader()
                Class aClass = classLoader.loadClass("nebula.plugin.responsible.TestFacetDefinition")
                System.out.println("Loaded $aClass")
            } catch (Exception e) {
                System.err.println("Could not load class $e")
            }
        }
    }
}
