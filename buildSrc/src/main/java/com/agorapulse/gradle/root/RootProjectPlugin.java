package com.agorapulse.gradle.root;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class RootProjectPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getExtensions().create("gradleProjects", ProjectsExtension.class);
    }

}
