package com.elly.athena.entity.npc;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RPGNPC_Renderer extends MobRenderer<RPGNPC, RPGNPC_RenderState, RPGNPC_Model> {
    public RPGNPC_Renderer(EntityRendererProvider.Context context) {
        super(context, new RPGNPC_Model(context.bakeLayer(ModelLayers.PLAYER)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(RPGNPC_RenderState rpgnpcRenderState) {
        return null;
    }

    @Override
    public RPGNPC_RenderState createRenderState() {
        return null;
    }
}
