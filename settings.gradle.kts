rootProject.name = "gradle-kotlin-libs-issue"

include("library")

project(":library").projectDir = file("libs/library")
project(":library").buildFileName = "build.gradle.kts"

gradle.projectsLoaded {
    rootProject.project(":library").pluginManager.apply("java-library")
}

