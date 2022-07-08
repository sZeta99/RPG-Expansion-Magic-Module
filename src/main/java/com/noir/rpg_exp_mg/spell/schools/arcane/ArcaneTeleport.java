package com.noir.rpg_exp_mg.spell.schools.arcane;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import com.noir.rpg_exp_mg.spell.preset.Teleport;
import com.noir.rpg_exp_mg.custom.tool.CoolDown;
import com.noir.rpg_exp_mg.custom.tool.Sound;
import com.noir.rpg_exp_mg.spell.ISpell;

public class ArcaneTeleport extends Item implements ISpell, IArcane {

    public ArcaneTeleport(Properties p) {
        super(p);
        // TODO Auto-generated constructor stub
    }

    public void releaseUsing(ItemStack itemstack, Level world, LivingEntity entity, int time) {

        if (entity instanceof Player) {
            Teleport tp = new Teleport(world, (Player) entity, 50);
            if (tp.run()) {

                Sound.playSound(world, (Player) entity, SoundEvents.ENDERMAN_TELEPORT);
                CoolDown.addCoolDown((Player) entity, itemstack.getItem(), 5);
                itemstack.hurtAndBreak(1, (Player) entity, (arg0) -> {
                    arg0.broadcastBreakEvent(entity.getUsedItemHand());
                });
            }
        }
    }

    public int getUseDuration(ItemStack p_40680_) {
        return 72000;
    }

    public UseAnim getUseAnimation(ItemStack p_40678_) {
        return UseAnim.BOW;
    }

    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);

        return InteractionResultHolder.consume(itemstack);

    }
}
