plugins {
    id("com.netflix.nebula.root")
}

dependencyLocking {
    lockAllConfigurations()
}

contacts {
    addPerson("nebula-plugins-oss@netflix.com") {
        moniker = "Nebula Plugins Maintainers"
        github = "nebula-plugins"
    }
}