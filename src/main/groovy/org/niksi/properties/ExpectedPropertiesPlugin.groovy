package org.niksi.properties

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.GradleException

class ExpectedPropertiesPlugin implements Plugin<Project> {
    def VALIDATION_TASK_NAME = 'validateProperties'
    def NEXT_TASK = 'preBuild'
    void apply(Project project) {
        println "Hello from ExpectPropertiesPlugin"
        //Fill values in remoteProperties
        def props = project.container(ExpectedProperty)
        props.all {
            if (project.hasProperty(name)) {
                value = project.property(name)
            }
        }
        project.extensions.remoteProperties = props
        //Define validation task
        project.task(VALIDATION_TASK_NAME) << {
            boolean mandatoryPropertyIsNotSet = false
            props.all {
                if (isMandatory && value == null) {
                    println "Property '$name' is mandatory to set up."

                    mandatoryPropertyIsNotSet = true
                }
            }
            if (mandatoryPropertyIsNotSet) {
                println "Please set up mandatory properties on this machine using one of the following ways:"
                println "..."
                throw new GradleException("Mandatory properties are not set")
            }
        }
        project.getTasksByName(NEXT_TASK, false).find().dependsOn(project.getTasksByName(VALIDATION_TASK_NAME, false).find())

        println "Bye from ExpectPropertiesPlugin"
    }
}
