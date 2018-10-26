package nebula.plugin.nothing

import nebula.test.ProjectSpec

class MoreNothingPluginSpec extends ProjectSpec {
    def 'apply plugin'() {
        when:
        project.plugins.apply(MoreNothingPlugin)

        then:
        noExceptionThrown()
    }

}