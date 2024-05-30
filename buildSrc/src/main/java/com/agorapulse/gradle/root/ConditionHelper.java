package com.agorapulse.gradle.root;

import org.gradle.api.Action;
import org.gradle.api.Project;

import java.util.function.Predicate;

class ConditionHelper {

    public final Predicate<Project> condition;
    public Action<Project> action;

    ConditionHelper(Predicate<Project> condition, Action<Project> action) {
        this.condition = condition;
        this.action = action;
    }

    void apply(Project project) {
        if (condition.test(project)) {
            action.execute(project);
        }
    }

}
