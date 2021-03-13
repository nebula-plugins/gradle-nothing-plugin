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

class PublishedPomInformationSpec extends Specification {
    def 'expected information in nebula pom'() {
        when:
        def path = 'build/publications/nebula/pom-default.xml'
        def pom
        try {
            pom = new XmlSlurper().parse(new File(path))
        } catch (Exception e) {
            throw new Exception('Run the "test" task from the main project to populate data', e)
        }

        then:
        assertContainsTopLevelDetails(pom)
        assertContainsLicense(pom)
        assertContainsContacts(pom)
        assertContainsDependencies(pom)
        assertContainsCustomizedInformation(pom)
    }

    def 'expected information in pluginMaven pom'() {
        when:
        def path = 'build/publications/pluginMaven/pom-default.xml'
        def pom
        try {
            pom = new XmlSlurper().parse(new File(path))
        } catch (Exception e) {
            throw new Exception('Run the "test" task from the main project to populate data', e)
        }

        then:
        assertContainsTopLevelDetails(pom)
        assertContainsLicense(pom)
        assertContainsContacts(pom)
        assertContainsDependencies(pom)
        assertContainsCustomizedInformation(pom)
    }

    private static void assertContainsTopLevelDetails(GPathResult pom) {
        assert pom.name == 'gradle-nothing-plugin'
        assert pom.artifactId == 'gradle-nothing-plugin'
        assert pom.description == 'Gradle plugins to do nothing'
        assert pom.groupId == 'com.netflix.nebula'
    }

    private static void assertContainsLicense(GPathResult pom) {
        assert pom.licenses.license.name.text() == 'The Apache Software License, Version 2.0'
        assert pom.licenses.license.url.text() == 'http://www.apache.org/licenses/LICENSE-2.0.txt'
        assert pom.licenses.license.distribution.text() == 'repo'
    }

    private static void assertContainsContacts(GPathResult pom) {
        assert pom.developers.developer.size() > 0
        assert pom.developers.developer[0].id.text() == 'nebula-plugins'
    }

    private static void assertContainsDependencies(GPathResult pom) {
        assert pom.dependencies.dependency.size() > 0

        assert pom.dependencies.dependency[0].groupId.text() == 'com.google.guava'
        assert pom.dependencies.dependency[0].artifactId.text() == 'guava'
        assert pom.dependencies.dependency[0].version.text().startsWith('28')


        assert pom.dependencies.dependency[1].groupId.text() == 'commons-logging'
        assert pom.dependencies.dependency[1].artifactId.text() == 'commons-logging'
        assert pom.dependencies.dependency[1].version.text() == '1.2'
    }

    private static void assertContainsCustomizedInformation(GPathResult pom) {
        assert pom.inceptionYear == '2014'
    }
}
