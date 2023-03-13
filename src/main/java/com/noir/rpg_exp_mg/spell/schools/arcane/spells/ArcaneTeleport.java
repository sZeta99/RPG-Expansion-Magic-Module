package com.noir.rpg_exp_mg.spell.schools.arcane.spells;

import com.noir.rpg_exp_mg.spell.ASpell;

//test class with teleport
public class ArcaneTeleport extends ASpell {

    // keep in mind enum for simil state machine

    // static factory method

    // comstructor
    public ArcaneTeleport(Properties properties) {
        super(properties);
        nextSpell(new ArcaneTeleportInstant(this));
    }

}