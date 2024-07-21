package net.ihor.changecolor.menu.widgets;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.function.Supplier;

public class IButtonWidget extends ButtonWidget {

    public static final NarrationSupplier DEFAULT_NARRATION_SUPPLIER = Supplier::get;
    public IButtonWidget(int x, int y, int width, int height, Text message, PressAction onPress, NarrationSupplier narrationSupplier) {
        super(x, y, width, height, message, onPress, narrationSupplier);
    }

    @Override
    protected void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
       if (this.isHovered()) {
           Color color = new Color(82, 82, 82, 110);
           context.fill(this.getX(), this.getY(), this.getX() + this.width, this.getY() + this.height, color.getRGB());
       }
    }
}
