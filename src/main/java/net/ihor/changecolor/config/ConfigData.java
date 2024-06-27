package net.ihor.changecolor.config;

import com.google.gson.annotations.Expose;

public class ConfigData {

    @Expose
    float red = 255.0f;
    @Expose
    float green = 255.0f;
    @Expose
    float blue = 255.0f;
    @Expose
    float alpha = 255.0f;
    @Expose
    boolean renderEyes = false;
    @Expose
    boolean renderDirection = false;

    public boolean isRenderEyes() {
        return renderEyes;
    }

    public boolean isRenderDirection() {
        return renderDirection;
    }

    public void setRenderEyes(boolean renderEyes) {
        this.renderEyes = renderEyes;
    }

    public void setRenderDirection(boolean renderDirection) {
        this.renderDirection = renderDirection;
    }

    public float getRed() {
        return red;
    }

    public float getGreen() {
        return green;
    }

    public float getBlue() {
        return blue;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setRed(float red) {
        this.red = red;
    }

    public void setGreen(float green) {
        this.green = green;
    }

    public void setBlue(float blue) {
        this.blue = blue;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
}
