plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(versionCatalogs.named("libs").findLibrary("commonsLang"))
}