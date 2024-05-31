import com.agorapulse.gradle.root.ProjectsExtension

plugins {
    id("com.agorapulse.gradle.root-project")
}

gradleProjects {
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