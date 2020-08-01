package com.arch_nexus.undersoul.client;

import com.arch_nexus.undersoul.client.block.BakedList;
import com.arch_nexus.undersoul.client.block.GlowBakedModel;
import com.arch_nexus.undersoul.mod.Data;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@EventBusSubscriber(modid = Data.MOD_ID, bus = EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEventSubscriber {

    public ClientForgeEventSubscriber() {
    }

    @SubscribeEvent
    public static void onModelBakeEvent(ModelBakeEvent event)
    {
        for (BlockState blockState : BakedList.mushroomGlowBlock.getStateContainer().getValidStates()) {
            ModelResourceLocation variantMRL = BlockModelShapes.getModelLocation(blockState);
            IBakedModel existingModel = event.getModelRegistry().get(variantMRL);
            if (existingModel == null) {
                LOGGER.warn("Did not find the expected vanilla baked model(s) for blockCamouflage in registry");
            } else if (existingModel instanceof GlowBakedModel) {
                LOGGER.warn("Tried to replace CamouflagedBakedModel twice");
            } else {
                GlowBakedModel customModel = new GlowBakedModel(existingModel);
                event.getModelRegistry().put(variantMRL, customModel);
            }
        }
    }
    
    private void onClientSetup(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(BakedList.mushroomGlowBlock, ClientForgeEventSubscriber::getDoubleLayer);
    }

    private static boolean getDoubleLayer(RenderType renderType) {
        return renderType == RenderType.getCutout() || renderType == RenderType.getTranslucent();
    }

    private static final Logger LOGGER = LogManager.getLogger();
}
