package com.zingersoft.module.misc;

import com.zingersoft.ZingerSoft;
import com.zingersoft.module.Module;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class ClickGUIModule extends Module {
    public ClickGUIModule() {
        super("ClickGUI", "Opens the click GUI", Category.MISC);
        setKey(Keyboard.KEY_RSHIFT);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        Minecraft.getMinecraft().displayGuiScreen(ZingerSoft.clickGUIScreen);
        setToggled(false);
    }
}

