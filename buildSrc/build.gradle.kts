plugins {
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("space.jasan:groovy-closure-support:0.6.3")
}

gradlePlugin {
    plugins {
        create("rootProjectPlugin") {
            id = "com.agorapulse.gradle.root-project"
            implementationClass = "com.agorapulse.gradle.root.RootProjectPlugin"
        }
    }
}