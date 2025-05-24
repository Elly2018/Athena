package com.elly.athena.entity.spell;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import org.jetbrains.annotations.NotNull;

public class WindSlashModel extends EntityModel<WindSlashRenderState> {
    private final ModelPart main;
    private final ModelPart second;

    public WindSlashModel(ModelPart root) {
        super(root);
        this.main = root.getChild("main");
        this.second = root.getChild("second");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild(
                "main",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4.0F, -4.0F, -1.0F, 2.0F, 8.0F, 4.0F),
                PartPose.ZERO
        );
        partdefinition.addOrReplaceChild(
                "second",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4.0F, -4.0F, -1.0F, 8.0F, 2.0F, 4.0F),
                PartPose.ZERO
        );
        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void setupAnim(@NotNull WindSlashRenderState renderState) {
        super.setupAnim(renderState);
        this.main.yRot = renderState.yRot * (float) (Math.PI / 180.0);
        this.main.xRot = renderState.xRot * (float) (Math.PI / 180.0);
        this.second.yRot = renderState.yRot * (float) (Math.PI / 180.0);
        this.second.xRot = renderState.xRot * (float) (Math.PI / 180.0);
        this.second.visible = renderState.state1 > 0.05F;
        this.main.z = renderState.state0;
        this.second.z = renderState.state1;
    }
}
