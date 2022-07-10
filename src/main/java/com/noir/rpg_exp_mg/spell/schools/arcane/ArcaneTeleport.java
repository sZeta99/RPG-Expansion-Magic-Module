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
import com.noir.rpg_exp_mg.items.ISpell;

public class ArcaneTeleport implements ISpell {

    private ISpell spell;

    public ArcaneTeleport() {

        this.spell = new ArcaneInstantTeleport();

    }

    @Override
    public void releaseUsing(ItemStack itemstaks, Level world, LivingEntity entity, int time) {
        spell.releaseUsing(itemstaks, world, entity, time);

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {

        return spell.use(world, player, hand);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return spell.getUseAnimation(itemStack);
    }

    public static boolean exe(Level world, Player player, Item item) {

        if (!player.getCooldowns().isOnCooldown(item)) {
            Teleport tp = new Teleport(world, player, 50);
            if (tp.run()) {
                CoolDown.addCoolDown(player, item, 50);
                Sound.playSound(world, player, SoundEvents.ENDERMAN_TELEPORT);

                return true;
            }
        }
        return false;
    }

    public ISpell getSpell() {
        return this.spell;
    }

    public void setSpell(ISpell spell) {
        this.spell = spell;
    }

    public class ArcaneInstantTeleport implements ISpell {

        private ArcaneChargeTeleport nextSpell() {
            return new ArcaneChargeTeleport();
        }

        public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {

            System.out.println("Instant");
            if (exe(world, player, player.getItemInHand(hand).getItem())) {
                System.out.println("Change spell");

                setSpell(nextSpell());

                System.out.println("Spell is Charge: " + (getSpell() instanceof ArcaneChargeTeleport));
            }
            return defaultuse(world, player, hand);

        }

        @Override
        public void releaseUsing(ItemStack itemstaks, Level world, LivingEntity entity, int i) {
            // TODO Auto-generated method stub

        }

        @Override
        public UseAnim getUseAnimation(ItemStack itemStack) {
            return UseAnim.EAT;
        }

    }

    public class ArcaneChargeTeleport implements ISpell {

        private ArcaneInstantTeleport nextSpell() {
            return new ArcaneInstantTeleport();
        }

        @Override
        public void releaseUsing(ItemStack itemstaks, Level world, LivingEntity entity, int time) {

            System.out.println("Hold relese Use");
            int i = itemstaks.getItem().getUseDuration(itemstaks) - time;
            System.out.println(i);
            if (i < 50)
                return;

            if (entity instanceof Player) {
                System.out.println("Hold");
                if (exe(world, (Player) entity, itemstaks.getItem())) {
                    System.out.println("Chanche hold");
                    setSpell(nextSpell());
                    System.out.println("Spell is instant: " + (getSpell() instanceof ArcaneInstantTeleport));
                }
            }

        }

        @Override
        public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {

            ItemStack itemstack = player.getItemInHand(hand);
            player.startUsingItem(hand);

            return InteractionResultHolder.consume(itemstack);

        }

        @Override
        public UseAnim getUseAnimation(ItemStack itemStack) {
            return UseAnim.BOW;
        }

    }

}
