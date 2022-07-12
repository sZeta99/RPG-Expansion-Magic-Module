package com.noir.rpg_exp_mg.spell;

import com.noir.rpg_exp_mg.spell.schools.ISchool;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public abstract class ASpell implements ISpell {

    private ISpell spell;
    private final ISchool school;

    public ASpell(ISchool school) {

        this.spell = nextSpell();
        this.school = school;

    }

    public ASpell() {

        this.spell = nextSpell();
        // Change to arcane as soon as possible
        this.school = null;

    }

    /**
     * Function call the omunimus method in spell
     * 
     * @param itemstaks
     * @param world
     * @param entity
     * @param time
     */
    @Override
    public void releaseUsing(ItemStack itemstaks, Level world, LivingEntity entity, int time) {
        spell.releaseUsing(itemstaks, world, entity, time);

    }

    /**
     * Function call the omunimus method in spell
     * 
     * @param world
     * @param player
     * @param hand
     * @return
     */
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {

        return spell.use(world, player, hand);
    }

    /**
     * Function call the omunimus method in spell
     * 
     * @param itemStack
     * @return
     */
    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return spell.getUseAnimation(itemStack);
    }

    /**
     * Spell getter
     * 
     * @return
     */
    public ISpell getSpell() {
        return this.spell;
    }

    public ISchool getSchool() {
        return this.school;
    }

    /**
     * Spell setter
     * 
     * @param spell
     */
    public void setSpell(ISpell spell) {
        this.spell = spell;
    }

    /**
     * This function will change the spell to the next one.
     * 
     * 
     * @return The next spell in the Graph of spell
     */
    public void changeSpell() {

        setSpell(spell.nextSpell());

    }

    /**
     * Parte di un interfaccia atta ad unificare spell e subSpell
     */
    @Override
    public ISpell nextSpell() {

        return startingSpell();
    }

    /**
     * 
     * @return The first subSpell of the spell
     */
    public abstract ISpell startingSpell();

}
