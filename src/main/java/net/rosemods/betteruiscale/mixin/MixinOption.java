package net.rosemods.betteruiscale.mixin;

import net.minecraft.client.AbstractOption;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.rosemods.betteruiscale.access.OptionAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AbstractOption.class)
public class MixinOption implements OptionAccess {
    @Final
    @Shadow
    private ITextComponent translatedBaseMessage;

    public ITextComponent getGenericLabel(int value) {
        return new TranslationTextComponent("options.generic_value", new Object[]{this.translatedBaseMessage, value});
    }
}
