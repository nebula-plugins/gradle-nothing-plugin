plugins {
    id("com.netflix.nebula.library")
}

description = "A Nebula library that does nothing"

dependencyLocking {
    lockAllConfigurations()
}

dependencies {
    implementation("org.jspecify:jspecify:1.0.0")
}