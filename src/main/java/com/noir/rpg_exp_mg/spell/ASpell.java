package com.noir.rpg_exp_mg.spell;

import com.noir.rpg_exp_mg.custom.tool.CoolDown;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

//Classe può essere spezzata ulteriormente
public abstract class ASpell extends Item implements ISpell {

    // Predisporre una NULL Spell
    private ISpell spell;

    /**
     * Default constructor
     * 
     * @param properties
     */
    public ASpell(Properties properties, ISpell spell) {
        super(properties);
        this.spell = spell;
    }

    public ASpell(Properties properties) {
        super(properties);
        this.spell = null;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int time) {
        // System.out.println("--------------Relese-----------------");
        if (entity instanceof Player) {
            Player player = (Player) entity;

            int i = this.getUseDuration(stack) - time;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(
                    stack, level, player, i,
                    true);
            if (i < 0 && spell != null && CoolDown.isCoolDown(player, this))
                return;

            spell.onRelease(stack, level, player, time);
        }

    }

    // Fondamentale per usare relese
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        if (spell != null || !CoolDown.isCoolDown(context.getPlayer(), this))
            return spell.onUseOn(context);

        return super.useOn(context);
    }

    @Override
    public boolean useOnRelease(ItemStack stack) {

        return super.useOnRelease(stack);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (spell != null || !CoolDown.isCoolDown(player, this))
            return spell.onUse(itemstack, level, player, hand);

        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemstack);

    }

    @Override
    public InteractionResultHolder<ItemStack> onUse(ItemStack itemStack, Level level, Player player,
            InteractionHand hand) {

        return spell.onUse(itemStack, level, player, hand);
    }

    @Override
    public InteractionResult onUseOn(UseOnContext context) {

        return spell.onUseOn(context);
    }

    @Override
    public boolean onUseOnRelease(ItemStack itemStack) {

        return spell.onUseOnRelease(itemStack);
    }

    public void onRelease(ItemStack itemStack, Level level, Player player, int time) {

        spell.onRelease(itemStack, level, player, time);
    }

    // Call it with the next spell
    public void nextSpell(ISpell nextSpell) {

        spell = nextSpell;
        System.out.println("ASpell.nextSpell() -> " + spell.getClass().getSimpleName());
    }
}
