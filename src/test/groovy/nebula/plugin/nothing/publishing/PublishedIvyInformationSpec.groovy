/**
 *
 *  Copyright 2018 Netflix, Inc.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 */

package nebula.plugin.nothing.publishing

import groovy.util.slurpersupport.GPathResult
import spock.lang.Specification

class PublishedIvyInformationSpec extends Specification {
    def 'expected information in pluginIvy descriptor file'() {
        when:
        def path = 'build/publications/pluginIvy/ivy.xml'
        def ivy
        try {
            ivy = new XmlSlurper().parse(new File(path))
        } catch (Exception e) {
            throw new Exception('Run the "test" task from the main project to populate data', e)
        }

        then:
        assertContainsTopLevelDetails(ivy)
        assertContainsDependencies(ivy)
    }

    private static void assertContainsTopLevelDetails(GPathResult ivy) {
        assert ivy.info.description.text().contains('Gradle plugins to do nothing')
        assert ivy.publications.artifact.@name == 'gradle-nothing-plugin'
    }

    private static void assertContainsDependencies(GPathResult ivy) {
        assert ivy.dependencies.dependency.size() > 0

        assert ivy.dependencies.dependency.first().@org.text() == 'com.google.collections'
        assert ivy.dependencies.dependency.first().@name.text() == 'google-collections'
        assert ivy.dependencies.dependency.first().@rev.text() == '1.0'


        assert ivy.dependencies.dependency[1].@org.text() == 'commons-logging'
        assert ivy.dependencies.dependency[1].@name.text() == 'commons-logging'
        assert ivy.dependencies.dependency[1].@rev.text() == '1.2'
    }
}
