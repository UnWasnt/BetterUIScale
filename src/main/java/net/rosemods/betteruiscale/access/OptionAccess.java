package net.rosemods.betteruiscale.access;


import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.text.ITextComponent;

public interface OptionAccess {
    ITextComponent getGenericLabel(int value);
    SliderPercentageOption DOUBLE_GUI_SCALE = new SliderPercentageOption("options.guiscale", 1.0D, 30.0D, 1.0F,
            (gameOptions) -> (double) gameOptions.guiScale,
            (gameOptions, scale) -> gameOptions.guiScale = scale.intValue(),
            (gameOptions, option) -> ((OptionAccess)option).getGenericLabel((int)option.get(gameOptions)));
}
