package com.noir.rpg_exp_mg.spell.schools.arcane;

import com.noir.rpg_exp_mg.spell.schools.ISchool;

import java.awt.Color;

final class SchoolArcane implements ISchool {

    private static String name;
    private static String description;
    private static Color color;

    public SchoolArcane() {
        this("Arcane",
                "Ancient magic losy in time",
                Color.GRAY);
    }

    private SchoolArcane(String name, String description, Color color) {
        SchoolArcane.name = name;
        SchoolArcane.description = description;
        SchoolArcane.color = color;
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
