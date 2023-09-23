package ru.komarov.crudwithvaadin.model;

import lombok.Getter;

public enum Status {
    IN_PROGRESS("In Progress"),
    DONE("Done"),
    PAUSED("Paused");
    @Getter
    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

}
