package com.arch_nexus.undersoul;

import com.arch_nexus.undersoul.common.init.block.ModBlocks;
import com.arch_nexus.undersoul.mod.Data;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Data.MOD_ID)
public class UnderSoul {

    public UnderSoul() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(modEventBus);
    }
}
