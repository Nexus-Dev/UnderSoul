package com.arch_nexus.undersoul.client.block;

import com.arch_nexus.undersoul.common.init.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class GlowBakedModel implements IBakedModel {

    public GlowBakedModel(IBakedModel iBakedModel)
    {
        defaultModel = iBakedModel.getBakedModel();
    }

    public static ModelDataMap getEmptyIModelData() {
        ModelDataMap.Builder builder = new ModelDataMap.Builder();
        ModelDataMap modelDataMap = builder.build();
        return modelDataMap;
    }

    /**
     * Forge's extension in place of IBakedModel::getQuads
     * It allows us to pass in some extra information which we can use to choose the appropriate quads to render
     * @param state
     * @param side
     * @param rand
     * @return
     */

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nullable Random rand) {
        List<BakedQuad> quads = this.getQuads(state, side, rand);
        if(MinecraftForgeClient.getRenderLayer() == RenderType.getTranslucent()) {
            for(int i = 0; i < quads.size(); i++) {
                BakedQuad quad = quads.get(i);
                int[] vertexData = quad.getVertexData();
                for(int j = 0; j < 4; j++) {
                    vertexData[8 * j + 6] = getLightValue(15, 15);
                }
                quads.set(i, new BakedQuad(vertexData, quad.getTintIndex(), quad.getFace(), quad.func_187508_a(), quad.func_239287_f_()));
            }
        }
        return quads;
    }

    private static final int UPPER_HALF = 65536;
    private static int getLightValue(int skyLighting, int blockLighting) {
        return UPPER_HALF * skyLighting * 16 + blockLighting * 16;
    }

    @Nonnull
    public IModelData getModelData()
    {
        ModelDataMap modelDataMap = getEmptyIModelData();
        return modelDataMap;
    }

    @Override
    public TextureAtlasSprite getParticleTexture(@Nonnull IModelData data)
    {
        return getActualBakedModelFromIModelData(data).getParticleTexture();
    }

    private IBakedModel getActualBakedModelFromIModelData(@Nonnull IModelData data) {
        IBakedModel retval = defaultModel;  // default
        return retval;
    }

    private IBakedModel defaultModel;


    // getTexture is used directly when player is inside the block.  The game will crash if you don't use something
    //   meaningful here.
    @Override
    public TextureAtlasSprite getParticleTexture() {
        return defaultModel.getParticleTexture();
    }

    // ideally, this should be changed for different blocks being camouflaged, but this is not supported by vanilla or forge
    @Override
    public boolean isAmbientOcclusion()
    {
        return defaultModel.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d()
    {
        return defaultModel.isGui3d();
    }

    @Override
    public boolean func_230044_c_() {
        return defaultModel.func_230044_c_();  // related to item "diffuselighting"
    }

    @Override
    public boolean isBuiltInRenderer()
    {
        return defaultModel.isBuiltInRenderer();
    }

    @Override
    public ItemOverrideList getOverrides()
    {
        return defaultModel.getOverrides();
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms()
    {
        return defaultModel.getItemCameraTransforms();
    }

    private static final Logger LOGGER = LogManager.getLogger();
    private static boolean loggedError = false; // prevent spamming console
}