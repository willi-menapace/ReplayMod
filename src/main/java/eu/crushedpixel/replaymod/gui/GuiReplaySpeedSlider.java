package eu.crushedpixel.replaymod.gui;

import eu.crushedpixel.replaymod.ReplayMod;
import eu.crushedpixel.replaymod.replay.ReplayHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.client.FMLClientHandler;

public class GuiReplaySpeedSlider extends GuiButton {

	private float sliderValue;

	private float valueStep, valueMin, valueMax;
	private String displayKey;
	private boolean dragging = false;

	public GuiReplaySpeedSlider(int buttonId, int p_i45017_2_, int p_i45017_3_, String displayKey)
	{
		super(buttonId, p_i45017_2_, p_i45017_3_, 150, 20, "");
		sliderValue = (9f/38f);

		this.width = 100;
		this.valueMin = 1;
		this.valueMax = 38;
		this.valueStep = 1;
		this.displayString = displayKey+": 1x";
		this.displayKey = displayKey;

		Minecraft.getMinecraft().getTextureManager().bindTexture(buttonTextures);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.drawTexturedModalRect(this.xPosition + (int)(sliderValue * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
		this.drawTexturedModalRect(this.xPosition + (int)(sliderValue * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
	}

	@Override
	protected int getHoverState(boolean mouseOver) {
		return 0;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY)
	{
		if (this.visible)
		{
			try {
				FontRenderer fontrenderer = mc.fontRendererObj;
				mc.getTextureManager().bindTexture(buttonTextures);
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
				int k = this.getHoverState(this.hovered);
				GlStateManager.enableBlend();
				GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
				GlStateManager.blendFunc(770, 771);
				this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + k * 20, this.width / 2, this.height);
				this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + k * 20, this.width / 2, this.height);
				this.mouseDragged(mc, mouseX, mouseY);
				int l = 14737632;

				if (packedFGColour != 0)
				{
					l = packedFGColour;
				}
				else if (!this.enabled)
				{
					l = 10526880;
				}
				else if (this.hovered && FMLClientHandler.instance().isGUIOpen(GuiMouseInput.class))
				{
					l = 16777120;
				}

				this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
			} catch(Exception e) {}
		}
	}

	private String translate(float f) {
		return f+"x";
	}

	public static float convertScaleRet(float value) {
		if(value <= 1) {
			return Math.round(value*10);
		}
		float steps = value-1;
		return Math.round(steps/0.25f);
	}

	public static float convertScale(float value) {
		if(value == 10) {
			return 1;
		}
		if(value <= 9) {
			return value/10f;
		}
		return 1+(0.25f*(value-10));
	}


	public double getSliderValue() {
		return convertScale(normalizedToReal(sliderValue));
	}

	@Override
	protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
		if (this.visible) {
			try {
				if(this.dragging) {
					sliderValue = (float)(mouseX - (this.xPosition + 4)) / (float)(this.width - 8);
					sliderValue = MathHelper.clamp_float(sliderValue, 0.0F, 1.0F);
					float f = denormalizeValue(sliderValue);
					sliderValue = normalizeValue(f);
					if(ReplayHandler.getSpeed() != 0) {
						ReplayHandler.setSpeed(convertScale(normalizedToReal(sliderValue)));
					}
					this.displayString = displayKey+": "+translate(convertScale(normalizedToReal(sliderValue)));
				}

				mc.getTextureManager().bindTexture(buttonTextures);
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				this.drawTexturedModalRect(this.xPosition + (int)(sliderValue * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
				this.drawTexturedModalRect(this.xPosition + (int)(sliderValue * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
			} catch(Exception e) {}
		}
	}

	public float normalizeValue(float p_148266_1_)
	{
		return MathHelper.clamp_float((this.snapToStepClamp(p_148266_1_) - this.valueMin) / (this.valueMax - this.valueMin), 0.0F, 1.0F);
	}

	public float realToNormalized(float value) {
		float min = 0 - valueMin;
		float max = valueMax + min;

		return value/(max) - min;
	}

	public float denormalizeValue(float p_148262_1_)
	{
		return this.snapToStepClamp(this.valueMin + (this.valueMax - this.valueMin) * MathHelper.clamp_float(p_148262_1_, 0.0F, 1.0F));
	}

	public float snapToStepClamp(float p_148268_1_)
	{
		p_148268_1_ = this.snapToStep(p_148268_1_);
		return MathHelper.clamp_float(p_148268_1_, this.valueMin, this.valueMax);
	}

	protected float snapToStep(float p_148264_1_)
	{
		if (this.valueStep > 0.0F)
		{
			p_148264_1_ = this.valueStep * (float)Math.round(p_148264_1_ / this.valueStep);
		}

		return p_148264_1_;
	}

	public float normalizedToReal(float value) {
		float min = 0 - valueMin;
		float max = valueMax + min;

		return Math.round(value*(max) - min);
	}

	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
	{
		if (super.mousePressed(mc, mouseX, mouseY))
		{
			this.dragging = true;
			return true;
		}
		else
		{
			return false;
		}
	}

	public void mouseReleased(int mouseX, int mouseY)
	{
		this.dragging = false;
	}

}