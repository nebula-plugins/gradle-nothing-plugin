package nebula.plugin.nothing

import com.google.common.collect.ImmutableList
import org.apache.commons.logging.Log
import org.apache.commons.logging.impl.NoOpLog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

/**
 * Does nothing.
 */
class NothingPlugin extends NoOpLog implements Plugin<Project> {

    Logger logger = Logging.getLogger(NothingPlugin);

    Project project

    void apply(Project project) {

        this.project = project

        logger.info("Hello, " + ImmutableList.of("friend").get(0));
    }
}
