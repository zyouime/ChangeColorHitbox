
package net.ihor.changecolor.mixin;

import net.ihor.changecolor.config.ModConfig;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public class EntityRendererDispatcherMixin {

    @Inject(method = "renderHitbox", at = @At("HEAD"), cancellable = true)
    private static void render(MatrixStack matrices, VertexConsumer vertices, Entity entity, float tickDelta, CallbackInfo ci) {
        ModConfig.loadConfig();
        Box box = entity.getBoundingBox().offset(-entity.getX(), -entity.getY(), -entity.getZ());
        if (entity instanceof PlayerEntity) {
            if (ModConfig.configData.isEnableRenderPlayer()) {
                WorldRenderer.drawBox(matrices, vertices, box,
                        ModConfig.configData.getPlayerColor()[0] / 255.0f,
                        ModConfig.configData.getPlayerColor()[1] / 255.0f,
                        ModConfig.configData.getPlayerColor()[2] / 255.0f,
                        ModConfig.configData.getPlayerColor()[3] / 255.0f);
            }
        } else if (entity instanceof PassiveEntity || entity instanceof BatEntity) {
            if (ModConfig.configData.isEnableRenderPassive()) {
                WorldRenderer.drawBox(matrices, vertices, box,
                        ModConfig.configData.getPassiveColor()[0] / 255.0f,
                        ModConfig.configData.getPassiveColor()[1] / 255.0f,
                        ModConfig.configData.getPassiveColor()[2] / 255.0f,
                        ModConfig.configData.getPassiveColor()[3] / 255.0f);
            }
        } else if (entity instanceof HostileEntity || entity instanceof GhastEntity || entity instanceof PhantomEntity || entity instanceof SlimeEntity) {
            if (ModConfig.configData.isEnableRenderMonster()) {
                WorldRenderer.drawBox(matrices, vertices, box,
                        ModConfig.configData.getMonsterColor()[0] / 255.0f,
                        ModConfig.configData.getMonsterColor()[1] / 255.0f,
                        ModConfig.configData.getMonsterColor()[2] / 255.0f,
                        ModConfig.configData.getMonsterColor()[3] / 255.0f);
            }
        } else if (entity instanceof ItemEntity) {
            if (ModConfig.configData.isEnableRenderDrop()) {
                WorldRenderer.drawBox(matrices, vertices, box,
                        ModConfig.configData.getDropColor()[0] / 255.0f,
                        ModConfig.configData.getDropColor()[1] / 255.0f,
                        ModConfig.configData.getDropColor()[2] / 255.0f,
                        ModConfig.configData.getDropColor()[3] / 255.0f);
            }
        } else if (entity instanceof ProjectileEntity) {
            if (ModConfig.configData.isEnableRenderProjectile()) {
                WorldRenderer.drawBox(matrices, vertices, box,
                        ModConfig.configData.getProjectileColor()[0] / 255.0f,
                        ModConfig.configData.getProjectileColor()[1] / 255.0f,
                        ModConfig.configData.getProjectileColor()[2] / 255.0f,
                        ModConfig.configData.getProjectileColor()[3] / 255.0f);
            }
        }
        if (entity instanceof EnderDragonEntity) {
            double d = -MathHelper.lerp((double)tickDelta, entity.lastRenderX, entity.getX());
            double e = -MathHelper.lerp((double)tickDelta, entity.lastRenderY, entity.getY());
            double f = -MathHelper.lerp((double)tickDelta, entity.lastRenderZ, entity.getZ());
            for (EnderDragonPart enderDragonPart : ((EnderDragonEntity)entity).getBodyParts()) {
                matrices.push();
                double g = d + MathHelper.lerp((double)tickDelta, enderDragonPart.lastRenderX, enderDragonPart.getX());
                double h = e + MathHelper.lerp((double)tickDelta, enderDragonPart.lastRenderY, enderDragonPart.getY());
                double i = f + MathHelper.lerp((double)tickDelta, enderDragonPart.lastRenderZ, enderDragonPart.getZ());
                matrices.translate(g, h, i);
                WorldRenderer.drawBox(matrices, vertices, enderDragonPart.getBoundingBox().offset(-enderDragonPart.getX(), -enderDragonPart.getY(), -enderDragonPart.getZ()), 0.25f, 1.0f, 0.0f, 1.0f);
                matrices.pop();
            }
        }
        if (ModConfig.configData.isRenderEyes()) {
            if (entity instanceof LivingEntity) {
                WorldRenderer.drawBox(matrices, vertices, box.minX, entity.getStandingEyeHeight() - 0.01f, box.minZ, box.maxX, entity.getStandingEyeHeight() + 0.01f, box.maxZ, 1.0f, 0.0f, 0.0f, 1.0f);
            }
        }
       if (ModConfig.configData.isRenderDirection()) {
           Vec3d vec3d = entity.getRotationVec(tickDelta);
           Matrix4f matrix4f = matrices.peek().getPositionMatrix();
           Matrix3f matrix3f = matrices.peek().getNormalMatrix();
           vertices.vertex(matrix4f, 0.0f, entity.getStandingEyeHeight(), 0.0f).color(0, 0, 255, 255).normal(matrix3f, (float)vec3d.x, (float)vec3d.y, (float)vec3d.z).next();
           vertices.vertex(matrix4f, (float)(vec3d.x * 2.0), (float)((double)entity.getStandingEyeHeight() + vec3d.y * 2.0), (float)(vec3d.z * 2.0)).color(0, 0, 255, 255).normal(matrix3f, (float)vec3d.x, (float)vec3d.y, (float)vec3d.z).next();
       }
       ci.cancel();
    }
}
