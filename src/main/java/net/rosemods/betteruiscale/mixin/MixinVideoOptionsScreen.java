package net.rosemods.betteruiscale.mixin;

import net.minecraft.client.AbstractOption;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.client.gui.screen.VideoSettingsScreen;
import net.minecraft.util.text.ITextComponent;
import net.rosemods.betteruiscale.access.OptionAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VideoSettingsScreen.class)
public class MixinVideoOptionsScreen extends SettingsScreen {
    @Shadow
    @Mutable @Final
    private static AbstractOption[] OPTIONS;

    public MixinVideoOptionsScreen(Screen parent, GameSettings gameOptions, ITextComponent title) {
        super(parent, gameOptions, title);
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void injectModifyOptions(CallbackInfo ci) {
        OPTIONS = new AbstractOption[]{AbstractOption.GRAPHICS, AbstractOption.RENDER_DISTANCE, AbstractOption.AO, AbstractOption.FRAMERATE_LIMIT, AbstractOption.VSYNC, AbstractOption.VIEW_BOBBING, OptionAccess.DOUBLE_GUI_SCALE, AbstractOption.ATTACK_INDICATOR, AbstractOption.GAMMA, AbstractOption.RENDER_CLOUDS, AbstractOption.FULLSCREEN, AbstractOption.PARTICLES, AbstractOption.MIPMAP_LEVELS, AbstractOption.ENTITY_SHADOWS, AbstractOption.SCREEN_EFFECT_SCALE_SLIDER, AbstractOption.ENTITY_DISTANCE_SCALING, AbstractOption.FOV_EFFECT_SCALE_SLIDER};
    }

    @Redirect(method = "mouseClicked(DDI)Z",
              at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;updateWindowSize()V"))
    private void redirectOnResolutionChanged(Minecraft client) {}

    @Inject(method = "mouseReleased(DDI)Z", at = @At("TAIL"))
    private void injectMouseReleased(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        this.minecraft.updateWindowSize();
    }
}
