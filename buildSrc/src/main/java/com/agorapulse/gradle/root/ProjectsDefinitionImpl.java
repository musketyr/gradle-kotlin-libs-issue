package com.agorapulse.gradle.root;

import org.gradle.api.Action;
import org.gradle.api.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class ProjectsDefinitionImpl implements ProjectsDefinition {
    private final List<ConditionHelper> conditions = new ArrayList<>();

    @Override
    public void dir(String dir, Action<Project> action) {
        conditions.add(new ConditionHelper(dirFunction(dir), action));
    }

    private Predicate<Project> dirFunction(String dir) {
        return project -> dir.equals(project.getProjectDir().getParentFile().getName());
    }

    Action<Project> asProjectConfigurer() {
        return project -> {
            for (ConditionHelper helper : conditions) {
                helper.apply(project);
            }
        };
    }

}
