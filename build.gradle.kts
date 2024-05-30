import com.agorapulse.gradle.root.ProjectsExtension

plugins {
    id("com.agorapulse.gradle.root-project")
}

extensions.configure<ProjectsExtension> {
    subprojects {
        dir("libs") {
            repositories {
                mavenCentral()
            }

            dependencies {
                add("implementation", libs.commonsLang)
            }
        }
    }
}