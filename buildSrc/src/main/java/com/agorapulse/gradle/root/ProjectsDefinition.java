package com.agorapulse.gradle.root;

import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import groovy.transform.stc.ClosureParams;
import groovy.transform.stc.SimpleType;
import org.gradle.api.Action;
import org.gradle.api.Project;
import space.jasan.support.groovy.closure.ConsumerWithDelegate;

import java.util.List;
import java.util.function.Predicate;

public interface ProjectsDefinition {

    void dir(String dir, Action<Project> action);

    default void dir(
        String dir,
        @DelegatesTo(value = Project.class, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType.class, options = "org.gradle.api.Project")
        Closure<?> configuration
    ) {
        dir(dir, p -> ConsumerWithDelegate.create(configuration).accept(p));
    }

}
