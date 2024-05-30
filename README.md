# Gradle Kotlin DSL Issue with Version Catalog

This repository is a minimal reproduction of an issue I'm facing with the Gradle Kotlin DSL and the version catalogs.

While in Groovy script the version catalog is available. 

[Build Scan](https://scans.gradle.com/s/mp6i4jrp7nsdq)


```groovy
plugins {
    id 'com.agorapulse.gradle.root-project'
}

gradleProjects {
    subprojects {
        dir('libs') {
            repositories {
                mavenCentral()
            }
            dependencies {
                implementation libs.commonsLang
            }
        }
    }
}
```

When using the Kotlin DSL, the version catalog is not available.

[Build Scan](https://scans.gradle.com/s/ulov2wx6g7kme)

```kotlin
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
```

The error message is:

```
org.gradle.api.UnknownDomainObjectException: Extension with name 'libs' does not exist. Currently registered extension names: [ext, base, defaultArtifacts, sourceSets, reporting, javaToolchains, java, testing]	
    at org.gradle.internal.extensibility.ExtensionsStorage.unknownExtensionException(ExtensionsStorage.java:148)	
    at org.gradle.internal.extensibility.ExtensionsStorage.getByName(ExtensionsStorage.java:125)	
    at org.gradle.internal.extensibility.DefaultConvention.getByName(DefaultConvention.java:187)	
    at org.gradle.kotlin.dsl.accessors.runtime.RuntimeKt.extensionOf(Runtime.kt:35)	
    at Build_gradle$1$1$1$2.invoke(build.gradle.kts:15)	
    at Build_gradle$1$1$1$2.invoke(build.gradle.kts:14)	
    at org.gradle.kotlin.dsl.ProjectExtensionsKt.dependencies(ProjectExtensions.kt:236)	
    at Build_gradle$1$1$1.execute(build.gradle.kts:14)	
    at Build_gradle$1$1$1.execute(build.gradle.kts:9)	
    at com.agorapulse.gradle.root.ConditionHelper.apply(ConditionHelper.java:20)	
    at com.agorapulse.gradle.root.ProjectsDefinitionImpl.lambda$asProjectConfigurer$1(ProjectsDefinitionImpl.java:25)	
    at org.gradle.api.internal.DefaultMutationGuard$1.execute(DefaultMutationGuard.java:45)	
    at org.gradle.internal.Actions.with(Actions.java:206)	
    at org.gradle.api.internal.project.BuildOperationCrossProjectConfigurator$1.run(BuildOperationCrossProjectConfigurator.java:69)	
    at org.gradle.internal.operations.DefaultBuildOperationRunner$1.execute(DefaultBuildOperationRunner.java:29)	
    at org.gradle.internal.operations.DefaultBuildOperationRunner$1.execute(DefaultBuildOperationRunner.java:26)	
    at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:66)	
    at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:59)	
    at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:157)	
    at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:59)	
    at org.gradle.internal.operations.DefaultBuildOperationRunner.run(DefaultBuildOperationRunner.java:47)	
    at org.gradle.internal.operations.DefaultBuildOperationExecutor.run(DefaultBuildOperationExecutor.java:68)	
    at org.gradle.api.internal.project.BuildOperationCrossProjectConfigurator.lambda$runProjectConfigureAction$0(BuildOperationCrossProjectConfigurator.java:66)	
    at org.gradle.api.internal.project.DefaultProjectStateRegistry$ProjectStateImpl.lambda$applyToMutableState$1(DefaultProjectStateRegistry.java:407)	
    at org.gradle.api.internal.project.DefaultProjectStateRegistry$ProjectStateImpl.fromMutableState(DefaultProjectStateRegistry.java:425)	
    at org.gradle.api.internal.project.DefaultProjectStateRegistry$ProjectStateImpl.applyToMutableState(DefaultProjectStateRegistry.java:406)	
    at org.gradle.api.internal.project.BuildOperationCrossProjectConfigurator.runProjectConfigureAction(BuildOperationCrossProjectConfigurator.java:66)	
    at org.gradle.api.internal.project.BuildOperationCrossProjectConfigurator.access$100(BuildOperationCrossProjectConfigurator.java:32)	
    at org.gradle.api.internal.project.BuildOperationCrossProjectConfigurator$BlockConfigureBuildOperation.run(BuildOperationCrossProjectConfigurator.java:111)	
    at org.gradle.internal.operations.DefaultBuildOperationRunner$1.execute(DefaultBuildOperationRunner.java:29)	
    at org.gradle.internal.operations.DefaultBuildOperationRunner$1.execute(DefaultBuildOperationRunner.java:26)	
    at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:66)	
    at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:59)	
    at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:157)	
    at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:59)	
    at org.gradle.internal.operations.DefaultBuildOperationRunner.run(DefaultBuildOperationRunner.java:47)	
    at org.gradle.internal.operations.DefaultBuildOperationExecutor.run(DefaultBuildOperationExecutor.java:68)	
    at org.gradle.api.internal.project.BuildOperationCrossProjectConfigurator.runBlockConfigureAction(BuildOperationCrossProjectConfigurator.java:62)	
    at org.gradle.api.internal.project.BuildOperationCrossProjectConfigurator.subprojects(BuildOperationCrossProjectConfigurator.java:48)	
    at org.gradle.api.internal.project.DefaultProject.subprojects(DefaultProject.java:751)	
    at org.gradle.api.internal.project.DefaultProject.subprojects(DefaultProject.java:746)	
    at com.agorapulse.gradle.root.ProjectsExtension.subprojects(ProjectsExtension.java:24)	
    at Build_gradle$1.invoke(build.gradle.kts:8)	
    at Build_gradle$1.invoke(build.gradle.kts:7)	
    at Build_gradle$inlined$sam$i$org_gradle_api_Action$0.execute(ExtensionContainerExtensions.kt)	
    at org.gradle.internal.extensibility.ExtensionsStorage$ExtensionHolder.configure(ExtensionsStorage.java:177)	
    at org.gradle.internal.extensibility.ExtensionsStorage.configureExtension(ExtensionsStorage.java:70)	
    at org.gradle.internal.extensibility.DefaultConvention.configure(DefaultConvention.java:202)	
    at Build_gradle.<init>(build.gradle.kts:20)
```
