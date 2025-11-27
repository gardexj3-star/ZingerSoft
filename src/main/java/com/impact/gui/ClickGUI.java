package com.zingersoft.gui;

import com.zingersoft.ZingerSoft;
import com.zingersoft.module.Module;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClickGUI extends GuiScreen {
    private List<Panel> panels = new ArrayList<>();

    public ClickGUI() {
        int x = 10;
        for (Module.Category category : Module.Category.values()) {
            panels.add(new Panel(category, x, 10, 100, 15));
            x += 110;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        
        for (Panel panel : panels) {
            panel.draw(mouseX, mouseY);
        }
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (Panel panel : panels) {
            panel.mouseClicked(mouseX, mouseY, mouseButton);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == 1) { // ESC
            mc.displayGuiScreen(null);
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    private class Panel {
        private Module.Category category;
        private int x, y, width, height;
        private boolean extended = true;
        private List<Module> modules;

        public Panel(Module.Category category, int x, int y, int width, int height) {
            this.category = category;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.modules = ZingerSoft.moduleManager.getModulesByCategory(category);
        }

        public void draw(int mouseX, int mouseY) {
            // Draw panel header
            drawRect(x, y, x + width, y + height, 0x80000000);
            fontRenderer.drawString(category.name, x + 2, y + 3, 0xFFFFFF);
            
            if (extended) {
                int moduleY = y + height;
                for (Module module : modules) {
                    if (module.isVisible()) {
                        int color = module.isToggled() ? 0xFF00FF00 : 0xFFFFFFFF;
                        drawRect(x, moduleY, x + width, moduleY + 12, 0x60000000);
                        fontRenderer.drawString(module.getName(), x + 2, moduleY + 2, color);
                        moduleY += 12;
                    }
                }
            }
        }

        public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
            if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
                if (mouseButton == 0) {
                    extended = !extended;
                }
            } else if (extended) {
                int moduleY = y + height;
                for (Module module : modules) {
                    if (module.isVisible() && mouseX >= x && mouseX <= x + width && 
                            mouseY >= moduleY && mouseY <= moduleY + 12) {
                        if (mouseButton == 0) {
                            module.toggle();
                        }
                        break;
                    }
                    moduleY += 12;
                }
            }
        }
    }
}

