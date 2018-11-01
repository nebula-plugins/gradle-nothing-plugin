## Generate the pom file used for plugin publishing

> **About the [`java-gradle-plugin`](https://docs.gradle.org/current/userguide/java_gradle_plugin.html)**
> The Java Gradle Plugin development plugin can be used to assist in the development of Gradle plugins.

The `java-gradle-plugin` generates a plugin publishing task, which we invoke to view the pom file used at plugin publication time:

```sh
./gradlew generatePomFileForPluginMavenPublication
```

**More information:**

The `java-gradle-plugin` configures the MavenPluginPublishPlugin and the IvyPluginPublishPlugin when [`maven-publish`](https://github.com/gradle/gradle/blob/master/subprojects/plugin-development/src/main/java/org/gradle/plugin/devel/plugins/JavaGradlePluginPlugin.java#L186) or [`ivy-publish`](https://github.com/gradle/gradle/blob/master/subprojects/plugin-development/src/main/java/org/gradle/plugin/devel/plugins/JavaGradlePluginPlugin.java#L192) plugins are applied.

The MavenPluginPublishPlugin creates the  [`pluginMaven`](https://github.com/gradle/gradle/blob/master/subprojects/plugin-development/src/main/java/org/gradle/plugin/devel/plugins/MavenPluginPublishPlugin.java#L73) publication that is then used for publishing the plugin.

## See which publications are available

Add the following to your build.gradle to see which publications are available:

**build.gradle**
```groovy
task availablePublications {
    project.tasks.each { task ->
        task.doLast {
            publishing.publications.each { println "Task ${task.path} knows about publication named '${it.name}'"}
        }
    }
}
```

and invoke with: 

```sh
./gradlew availablePublications
```

---

## Useful publishing commands to preview generated pom-file/ivy-descriptor

- publication used for the Gradle plugin portal: `generatePomFileForPluginMavenPublication`
    - used by Gradle's `java-gradle-plugin` 
- publication used for internal or local maven repository: `generatePomFileForNebulaPublication`
    - used by Gradle's [`maven-publish`](https://docs.gradle.org/current/userguide/publishing_maven.html#publishing_maven:tasks)
- publication used for internal or local ivy repositoy: `generateDescriptorFileForPluginIvyPublication`
    - used by Gradle's [`ivy-publish`](https://docs.gradle.org/current/userguide/publishing_ivy.html#publishing_ivy:tasks)
