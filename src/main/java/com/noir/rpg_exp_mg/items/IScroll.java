package com.noir.rpg_exp_mg.items;

import com.noir.rpg_exp_mg.spell.ASpell;
import com.noir.rpg_exp_mg.spell.ISpell;

public class IScroll extends ASpell {

    // static factory method
    public static IScroll createScroll(ISpell spell) {
        return new IScroll(new Properties(), spell);
    }

    public IScroll(Properties properties, ISpell spell) {
        super(properties, spell);
    }

    // Costruttore per il bus
    public IScroll(Properties properties) {
        super(properties, null);
    }

}
