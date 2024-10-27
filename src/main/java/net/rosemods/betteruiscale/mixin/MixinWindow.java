package net.rosemods.betteruiscale.mixin;

import net.minecraft.client.MainWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MainWindow.class)
public class MixinWindow {
    @Shadow
    private int framebufferWidth;

    @Shadow
    private int framebufferHeight;

    @Shadow
	private double guiScaleFactor;

    @Shadow
	private int scaledWidth;

    @Shadow
	private int scaledHeight;

    /**
     * @author Rose
	 * @reason Modifies gui scaling
     */
    @Overwrite
    public int calcGuiScale(int guiScale, boolean forceUnicodeFont) {
		int i;
		for(i = 1; i != guiScale && i < this.framebufferWidth && i < this.framebufferHeight && this.framebufferWidth / (i + 1) >= 40 && this.framebufferHeight / (i + 1) >= 30; ++i) {
		}

		if (forceUnicodeFont && i % 2 != 0) {
			++i;
		}

		return i;
	}

	/**
	 * @author Rose
	 * @reason Modifies gui scaling
	 */
	@Overwrite
	public void setGuiScale(double guiScaleFactor) {
		if(guiScaleFactor > 2) guiScaleFactor = 1.5 * (0.3 + Math.log10(guiScaleFactor));
		else guiScaleFactor = 1 + (guiScaleFactor * 0.075);
		//scaleFactor = (scaleFactor + 3) / 4;
		this.guiScaleFactor = guiScaleFactor;
		int i = (int)((double)this.framebufferWidth / guiScaleFactor);
		this.scaledWidth = (double)this.framebufferWidth / guiScaleFactor > (double)i ? i + 1 : i;
		int j = (int)((double)this.framebufferHeight / guiScaleFactor);
		this.scaledHeight = (double)this.framebufferHeight / guiScaleFactor > (double)j ? j + 1 : j;
	}
}
