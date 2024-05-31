dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

rootProject.name = "gradle-kotlin-libs-issue"

include("library")

project(":library").projectDir = file("libs/library")
project(":library").buildFileName = "library.gradle.kts"

