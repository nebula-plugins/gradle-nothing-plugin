package nebula.plugin.nothing

import com.google.common.collect.ImmutableList
import nebula.plugin.responsible.FacetDefinition
import nebula.plugin.responsible.TestFacetDefinition
import nebula.plugin.responsible.NebulaFacetPlugin
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
            project.getPlugins().apply(NebulaFacetPlugin)
            NamedDomainObjectContainer<FacetDefinition> facetDefinitions = (NamedDomainObjectContainer<FacetDefinition>) project.getExtensions().getByName("facets")
            TestFacetDefinition smokeTestFacet = new TestFacetDefinition("smokeTest")
            smokeTestFacet.setTestTaskName("smokeTest")
            smokeTestFacet.setIncludeInCheckLifecycle(true)
            smokeTestFacet.setParentSourceSet("test")
            facetDefinitions.add(smokeTestFacet)
        }
    }
}
