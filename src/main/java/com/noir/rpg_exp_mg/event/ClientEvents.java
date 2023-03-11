package com.noir.rpg_exp_mg.event;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.ScreenEvent;

import com.noir.rpg_exp_mg.RpgExpansionMagicModuleMod;
import com.noir.rpg_exp_mg.networking.ModMessages;
import com.noir.rpg_exp_mg.networking.packet.DrinkWaterC2SPacket;
import com.noir.rpg_exp_mg.util.KeyBinding;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = RpgExpansionMagicModuleMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.KeyInputEvent event) {
            if (KeyBinding.DRINKING_KEY.consumeClick()) {
                ModMessages.sendToServer(new DrinkWaterC2SPacket());
            }
        }
    }

}