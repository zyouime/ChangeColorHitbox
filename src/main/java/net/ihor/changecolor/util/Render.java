package net.ihor.changecolor.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import org.joml.Quaternionf;

public class Render {

    public static void drawEntity2(int x, int y, int size, LivingEntity entity) {
        MatrixStack matrixStack = RenderSystem.getModelViewStack();
        matrixStack.push();
        matrixStack.translate((float)x, (float)y, 1050.0F);
        matrixStack.scale(1.0F, 1.0F, 1.0F);
        RenderSystem.applyModelViewMatrix();
        MatrixStack matrixStack2 = new MatrixStack();
        matrixStack2.translate(0.0F, 0.0F, 1000.0F);
        matrixStack2.scale((float)size, (float)size, (float)size);
        Quaternionf quaternionf = (new Quaternionf()).rotateZ(3.1415927F);
        matrixStack2.multiply(quaternionf);
        float h = entity.bodyYaw;
        float i = entity.getYaw();
        float j = entity.getPitch();
        float k = entity.prevHeadYaw;
        float l = entity.headYaw;

        entity.bodyYaw = 0.0f;
        entity.setYaw(0.0f);
        entity.setPitch(0.0f);
        entity.headYaw = 0.0f;
        entity.prevHeadYaw = 0.0f;
        DiffuseLighting.method_34742();
        EntityRenderDispatcher entityRenderDispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();
        entityRenderDispatcher.setRenderShadows(false);
        VertexConsumerProvider.Immediate immediate = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
        RenderSystem.disableDepthTest();
        entityRenderDispatcher.setRenderHitboxes(true);
        RenderSystem.runAsFancy(() -> {
            entityRenderDispatcher.render(entity, 0.0, 0.0, 0.0, 0.0F, 1.0F, matrixStack2, immediate, 15728880);
            Box box = entity.getBoundingBox().offset(-entity.getX(), -entity.getY(), -entity.getZ());
            WorldRenderer.drawBox(matrixStack2, immediate.getBuffer(RenderLayer.LINES), box, 255.0f, 255.0f, 255.0f, 255.0f);
        });
        immediate.draw();
        entityRenderDispatcher.setRenderShadows(true);
        entity.bodyYaw = h;
        entity.setYaw(i);
        entity.setPitch(j);
        entity.prevHeadYaw = k;
        entity.headYaw = l;
        matrixStack.pop();
        RenderSystem.applyModelViewMatrix();
        DiffuseLighting.enableGuiDepthLighting();
    }
    public static void drawEntity(int x, int y, float scale, LivingEntity entity) {
        drawEntity2(x, y, (int) scale, entity);
    }

    public static void drawShadowString(DrawContext context, TextRenderer textRenderer, float scale, Text text, float x, float y, int color) {
        context.getMatrices().push();
        context.getMatrices().scale(scale, scale, scale);
        context.drawTextWithShadow(textRenderer, text, (int) (x / scale), (int) (y / scale), color);
        context.getMatrices().pop();
    }
}