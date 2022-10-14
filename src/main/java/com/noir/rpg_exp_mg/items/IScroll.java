package com.noir.rpg_exp_mg.items;

import com.noir.rpg_exp_mg.spell.ASpell;

public class IScroll extends ASpell {

    // static factory method
    public static IScroll createScroll(ASpell spell) {
        return new IScroll(new Properties(), spell);
    }

    public IScroll(Properties properties, ASpell spell) {
        super(properties, spell);
    }

    // Costruttore per il bus
    public IScroll(Properties properties) {
        super(properties, null);
    }

}
