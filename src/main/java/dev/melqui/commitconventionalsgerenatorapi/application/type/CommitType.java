package dev.melqui.commitconventionalsgerenatorapi.application.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommitType {

    FEATURE("feat"),
    CHORE("chore"),
    FIX("fix");

    private final String value;
}
