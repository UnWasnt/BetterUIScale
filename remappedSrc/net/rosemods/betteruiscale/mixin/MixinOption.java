package net.rosemods.betteruiscale.mixin;

import net.minecraft.client.Option;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.rosemods.betteruiscale.access.OptionAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Option.class)
public class MixinOption implements OptionAccess {
    @Final
    @Shadow
    private Component key;

    public Component getGenericLabel(int value) {
        return new TranslatableComponent("options.generic_value", new Object[]{this.key, value});
    }
}
