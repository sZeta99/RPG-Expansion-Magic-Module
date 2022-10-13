package com.noir.rpg_exp_mg.spell;

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

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int time) {
        System.out.println("--------------Relese-----------------");
        if (entity instanceof Player) {
            Player player = (Player) entity;

            int i = this.getUseDuration(stack) - time;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(
                    stack, level, player, i,
                    true);
            if (i < 0)
                return;

            // Uso di un metodo come interfaccia
            onUseOnRelease(stack, level, player, time);
        }

    }

    // Fondamentale per usare relese
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        onUseOn(context);

        return super.useOn(context);
    }

    @Override
    public boolean useOnRelease(ItemStack stack) {

        onUseOnRelease(stack);
        return super.useOnRelease(stack);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        onUse(itemstack, level, player, hand);

        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemstack);

    }

    // Seguono i metodi interfaccia per la creazionie di una spell
    // Bosigna inserire una metotologia per passare questi parametri prendendoli da
    // una spell
    @Override
    public ISpell nextSpell() {

        return spell.nextSpell();
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

    @Override
    public void onUseOnRelease(ItemStack itemStack, Level level, Player player, int time) {

        spell.onUseOnRelease(itemStack, level, player, time);
    }

}
