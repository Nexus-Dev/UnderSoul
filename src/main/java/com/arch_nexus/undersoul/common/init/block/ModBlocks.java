package com.arch_nexus.undersoul.common.init.block;

import com.arch_nexus.undersoul.common.block.MushroomPlantBlock;
import com.arch_nexus.undersoul.common.block.SoulStoneBlock;
import com.arch_nexus.undersoul.mod.Data;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Data.MOD_ID);

    public static final RegistryObject<Block> SOUL_STONE = BLOCKS.register("soul_stone", () -> new SoulStoneBlock());
    public static final RegistryObject<Block> SOUL_STONE_BRICKS = BLOCKS.register("soul_stone_bricks", () -> new SoulStoneBlock());
    public static final RegistryObject<Block> CRACKED_SOUL_STONE_BRICKS = BLOCKS.register("cracked_soul_stone_bricks", () -> new SoulStoneBlock());
    public static final RegistryObject<Block> CHISELED_SOUL_STONE_BRICKS = BLOCKS.register("chiseled_soul_stone_bricks", () -> new SoulStoneBlock());
    public static final RegistryObject<Block> ACTIVATED_CHISELED_SOUL_STONE_BRICKS = BLOCKS.register("activated_chiseled_soul_stone_bricks", () -> new SoulStoneBlock());

    public static final RegistryObject<Block> PURPLE_MUSHROOM = BLOCKS.register("purple_mushroom", () -> new MushroomPlantBlock());
}
