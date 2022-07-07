package com.noir.rpg_exp_mg.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import com.noir.rpg_exp_mg.spell.preset.Teleport;

public class Scroll extends Item {
    public Scroll(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player,
            InteractionHand hand) {

        Teleport tp = new Teleport(world, player, 50, 10, this);
        tp.run();
        return super.use(world, player, hand);
    }

}