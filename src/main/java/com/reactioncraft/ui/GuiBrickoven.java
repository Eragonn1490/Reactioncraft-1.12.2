package com.reactioncraft.ui;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.containers.ContainerBrickOven;
import com.reactioncraft.tiles.TileEntityBrickOven;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBrickoven extends UIContainerBase
{
    private static final ResourceLocation BRICK_OVEN_TEXTURE = new ResourceLocation(Reactioncraft.MODID,"textures/gui/brickoven.png");
    /** The player inventory bound to this GUI. */
    private final InventoryPlayer playerInventory;
    private final TileEntityBrickOven brickoven;

    public GuiBrickoven(InventoryPlayer playerInv, TileEntityBrickOven furnaceInv)
    {
        super(new ContainerBrickOven(playerInv, furnaceInv));
        this.playerInventory = playerInv;
        this.brickoven = furnaceInv;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = this.brickoven.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(BRICK_OVEN_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        int k = this.getBurnLeftScaled(13);
        this.drawTexturedModalRect(i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);

        int l = this.getCookProgressScaled(24);
        this.drawTexturedModalRect(i + 79, j + 34, 176, 14, l + 1, 16);
    }

    private int getCookProgressScaled(int pixels)
    {
        int i = this.brickoven.totalCookTime;
        int j =TileEntityBrickOven.OVEN_PROCESS_TIME;
        return i != 0 ? i * pixels / j : 0;
    }

    private int getBurnLeftScaled(int pixels)
    {
        int i = this.brickoven.currentItemBurnTime;
        int fuelTime=brickoven.burnTime;
        return (fuelTime!=0 && i!=0)   ? i* pixels / fuelTime : -1;
    }
}