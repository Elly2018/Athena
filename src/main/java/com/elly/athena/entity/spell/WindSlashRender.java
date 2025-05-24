package com.elly.athena.entity.spell;

import com.elly.athena.Athena;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class WindSlashRender extends EntityRenderer<WindSlash, WindSlashRenderState> {
    WindSlashModel model;
    //private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "textures/entity/spell/wind_slash.png");
    private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/shulker/spark.png");
    private static final RenderType RENDER_TYPE = RenderType.entitySolid(TEXTURE_LOCATION);
    public static final ModelLayerLocation layer = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "wind_slash"), "main");

    public WindSlashRender(EntityRendererProvider.Context context) {
        super(context);
        model = new WindSlashModel(context.bakeLayer(layer));
    }

    @Override
    public @NotNull WindSlashRenderState createRenderState() {
        return new WindSlashRenderState();
    }

    public void render(WindSlashRenderState state, PoseStack stack, MultiBufferSource source, int packedLight) {
        stack.pushPose();
        stack.mulPose(Axis.YP.rotationDegrees(state.yRot - 90.0F));
        stack.mulPose(Axis.ZP.rotationDegrees(state.xRot));
        stack.scale(2F, 2F, 2F);
        this.model.setupAnim(state);;
        VertexConsumer vertexconsumer = source.getBuffer(RENDER_TYPE);
        this.model.renderToBuffer(stack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 654311423);
        stack.popPose();
        super.render(state, stack, source, packedLight);
    }

    public void extractRenderState(@NotNull WindSlash entity, @NotNull WindSlashRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.yRot = entity.getYRot(partialTick);
        state.xRot = entity.getXRot(partialTick);
        state.state0 = entity.GetState0();
        state.state1 = entity.GetState1();
    }
}
