package net.rosemods.betteruiscale.access;

import net.minecraft.client.ProgressOption;
import net.minecraft.network.chat.Component;

public interface OptionAccess {
    Component getGenericLabel(int value);
    ProgressOption DOUBLE_GUI_SCALE = new ProgressOption("options.guiscale", 1.0D, 30.0D, 1.0F,
            (gameOptions) -> { return (double) gameOptions.guiScale;},
            (gameOptions, scale) -> { gameOptions.guiScale = scale.intValue(); },
            (gameOptions, option) -> { return ((OptionAccess)option).getGenericLabel((int)option.get(gameOptions));
	});
}
