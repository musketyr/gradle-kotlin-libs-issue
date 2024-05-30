package com.agorapulse.gradle.root;

import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import groovy.transform.stc.ClosureParams;
import groovy.transform.stc.SimpleType;
import org.gradle.api.Action;
import org.gradle.api.Project;
import space.jasan.support.groovy.closure.ConsumerWithDelegate;

import javax.inject.Inject;

public abstract class ProjectsExtension {
    private final Project project;

    @Inject
    public ProjectsExtension(Project project) {
        this.project = project;
    }

    public void subprojects(Action<ProjectsDefinition> action) {
        ProjectsDefinitionImpl projects = new ProjectsDefinitionImpl();
        action.execute(projects);
        project.subprojects(projects.asProjectConfigurer());
    }

    public void subprojects(
        @DelegatesTo(value = ProjectsDefinition.class, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType.class, options = "com.agorapulse.gradle.root.ProjectsDefinition")
        Closure<?> configuration
    ) {
        subprojects(p -> ConsumerWithDelegate.create(configuration).accept(p));
    }

}
