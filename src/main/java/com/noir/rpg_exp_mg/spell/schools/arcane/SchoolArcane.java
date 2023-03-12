package com.noir.rpg_exp_mg.spell.schools.arcane;

import com.noir.rpg_exp_mg.spell.schools.ISchool;

import java.awt.Color;

public final class SchoolArcane implements ISchool {

    private String name;
    private String description;
    private Color color;

    public SchoolArcane() {
        this("Arcane",
                "Ancient magic losy in time",
                Color.GRAY);

    }

    private SchoolArcane(String name, String description, Color color) {
        this.name = name;
        this.description = description;
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
