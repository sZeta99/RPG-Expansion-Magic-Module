package com.noir.rpg_exp_mg.spell;

public interface EnumSpell {

    ISpell spell = null;

    default ISpell getSpell() {
        return spell;
    }
}
