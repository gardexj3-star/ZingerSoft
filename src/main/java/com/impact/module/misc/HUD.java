package com.zingersoft.module.misc;

import com.zingersoft.module.Module;

public class HUD extends Module {
    public HUD() {
        super("HUD", "Shows overlay information", Category.MISC);
        setToggled(true);
    }
}

