package com.noir.rpg_exp_mg.spell.schools.arcane;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import com.noir.rpg_exp_mg.spell.preset.Teleport;
import com.noir.rpg_exp_mg.custom.tool.CoolDown;
import com.noir.rpg_exp_mg.custom.tool.Sound;
import com.noir.rpg_exp_mg.items.ISpell;

public class ArcaneTeleport implements ISpell {

    private ISpell spell;
    private ISpell nextSpell;

    public ArcaneTeleport() {

        this.spell = new ArcaneInstantTeleport();
        this.nextSpell = new ArcaneChargeTeleport();
    }

    @Override
    public void releaseUsing(ItemStack itemstaks, Level world, LivingEntity entity, int i) {
        spell.releaseUsing(itemstaks, world, entity, i);

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {

        return spell.use(world, player, hand);
    }

    public static void exe(Level world, Player player, Item item) {

        Teleport tp = new Teleport(world, player, 50);
        if (tp.run()) {

            Sound.playSound(world, player, SoundEvents.ENDERMAN_TELEPORT);
            CoolDown.addCoolDown(player, item, 5);

        }
    }

    public ISpell getSpell() {
        return this.spell;
    }

    public void setSpell(ISpell spell) {
        this.spell = spell;
    }

    public ISpell getNextSpell() {
        return this.nextSpell;
    }

    public void setNextSpell(ISpell nextSpell) {
        this.nextSpell = nextSpell;
    }

    public class ArcaneInstantTeleport implements ISpell {

        public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {

            exe(world, player, player.getItemInHand(hand).getItem());
            setSpell(getNextSpell());
            setNextSpell(new ArcaneInstantTeleport());
            return defaultuse(world, player, hand);

        }

        @Override
        public void releaseUsing(ItemStack itemstaks, Level world, LivingEntity entity, int i) {
            // TODO Auto-generated method stub

        }

    }

    public class ArcaneChargeTeleport implements ISpell {

        @Override
        public void releaseUsing(ItemStack itemstaks, Level world, LivingEntity entity, int i) {
            if (entity instanceof Player) {
                exe(world, (Player) entity, itemstaks.getItem());
                setSpell(getNextSpell());
                setNextSpell(new ArcaneChargeTeleport());
            }

        }

        public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
            System.out.println("Used");
            ItemStack itemstack = player.getItemInHand(hand);
            player.startUsingItem(hand);

            return InteractionResultHolder.consume(itemstack);

        }

    }

}
