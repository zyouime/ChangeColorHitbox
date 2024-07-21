package net.ihor.changecolor.config;

import com.google.gson.annotations.Expose;

public class ConfigData {

    @Expose
    float[] playerColor = {255f, 255f, 255f, 255f};
    @Expose
    boolean enableRenderPlayer = true;
    @Expose
    float[] monsterColor = {255f, 255f, 255f, 255f};
    @Expose
    boolean enableRenderMonster = true;
    @Expose
    float[] passiveColor = {255f, 255f, 255f, 255f};
    @Expose
    boolean enableRenderPassive = true;
    @Expose
    float[] dropColor = {255f, 255f, 255f, 255f};
    @Expose
    boolean enableRenderDrop = true;
    @Expose
    float[] projectileColor = {255f, 255f, 255f, 255f};
    @Expose
    boolean enableRenderProjectile = true;
    @Expose
    boolean renderDirection = false;
    @Expose
    boolean renderEyes = false;

    public boolean isRenderDirection() {
        return renderDirection;
    }

    public boolean isRenderEyes() {
        return renderEyes;
    }

    public void setRenderEyes(boolean renderEyes) {
        this.renderEyes = renderEyes;
    }

    public void setRenderDirection(boolean renderDirection) {
        this.renderDirection = renderDirection;
    }

    public void setEnableRenderProjectile(boolean enableRenderProjectile) {
        this.enableRenderProjectile = enableRenderProjectile;
    }

    public void setProjectileColor(float[] projectileColor) {
        this.projectileColor = projectileColor;
    }

    public boolean isEnableRenderProjectile() {
        return enableRenderProjectile;
    }

    public float[] getProjectileColor() {
        return projectileColor;
    }

    public void setEnableRenderPlayer(boolean enableRenderPlayer) {
        this.enableRenderPlayer = enableRenderPlayer;
    }

    public void setEnableRenderMonster(boolean enableRenderMonster) {
        this.enableRenderMonster = enableRenderMonster;
    }

    public void setEnableRenderPassive(boolean enableRenderPassive) {
        this.enableRenderPassive = enableRenderPassive;
    }

    public void setEnableRenderDrop(boolean enableRenderDrop) {
        this.enableRenderDrop = enableRenderDrop;
    }

    public boolean isEnableRenderPlayer() {
        return enableRenderPlayer;
    }

    public boolean isEnableRenderMonster() {
        return enableRenderMonster;
    }

    public boolean isEnableRenderPassive() {
        return enableRenderPassive;
    }

    public boolean isEnableRenderDrop() {
        return enableRenderDrop;
    }

    public float[] getMonsterColor() {
        return monsterColor;
    }

    public float[] getPassiveColor() {
        return passiveColor;
    }

    public float[] getDropColor() {
        return dropColor;
    }

    public float[] getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(float[] playerColor) {
        this.playerColor = playerColor;
    }

    public void setMonsterColor(float[] monsterColor) {
        this.monsterColor = monsterColor;
    }

    public void setPassiveColor(float[] passiveColor) {
        this.passiveColor = passiveColor;
    }

    public void setDropColor(float[] dropColor) {
        this.dropColor = dropColor;
    }



}
