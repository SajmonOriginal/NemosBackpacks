package com.devnemo.nemos.backpacks.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.devnemo.nemos.backpacks.config.BackpackConfig;
import com.devnemo.nemos.backpacks.world.item.BackpackItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import static com.devnemo.nemos.backpacks.NemosBackpacks.BACKPACK_SLOT;

public class BackpackFeature extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    public BackpackFeature(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> context) {
        super(context);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, AbstractClientPlayer player,
                       float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!BackpackConfig.shouldRenderBackpackOnBack()) {
            return;
        }

        ItemStack backpack = player.getInventory().getItem(BACKPACK_SLOT);
        if (!(backpack.getItem() instanceof BackpackItem)) {
            return;
        }

        if (Minecraft.getInstance().level == null) {
            return;
        }

        poseStack.pushPose();

        // Attach to body so it follows body rotation (leaning, swimming, etc.)
        this.getParentModel().body.translateAndRotate(poseStack);

        // Flip upright (body space is upside down relative to world)
        poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(180));
        // Position: Y negative = up in flipped space, Z negative = towards back
        poseStack.translate(0.0, -0.35, -0.15);
        poseStack.scale(0.6f, 0.6f, 0.6f);

        Minecraft.getInstance().getItemRenderer().renderStatic(
                backpack,
                ItemDisplayContext.FIXED,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                bufferSource,
                player.level(),
                0);
        poseStack.popPose();
    }
}
