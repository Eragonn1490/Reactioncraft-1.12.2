package com.reactioncraft.craftinghandlers;

import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class CraftablesCraftingHandler
{
    @SuppressWarnings("unused")
	@SubscribeEvent
    public void onCrafting(ItemCraftedEvent event)
    {
        int G;
        ItemStack j;
        ItemStack k;
        ItemStack irondust = new ItemStack(ItemIndex.ironShavings);

        for (G = 0; G < event.craftMatrix.getSizeInventory(); ++G)
        {
            if (!event.craftMatrix.getStackInSlot(G).isEmpty())
            {
                j = event.craftMatrix.getStackInSlot(G);

                if (j.getItem() == ItemIndex.goldChisel)
                {
                    k = new ItemStack(ItemIndex.goldChisel, 2, j.getItemDamage() + 1);

                    if (k.getItemDamage() >= k.getMaxDamage())
                    {
                        k.shrink(1);
                    }

                    event.craftMatrix.setInventorySlotContents(G, k);
                }
            }
        }

        for (G = 0; G < event.craftMatrix.getSizeInventory(); ++G)
        {
            if (!event.craftMatrix.getStackInSlot(G) .isEmpty())
            {
                j = event.craftMatrix.getStackInSlot(G);

                if (j.getItem() == ItemIndex.flintChisel)
                {
                    k = new ItemStack(ItemIndex.flintChisel, 2, j.getItemDamage() + 1);

                    if (k.getItemDamage() >= k.getMaxDamage())
                    {
                       k.shrink(1);
                    }

                    event.craftMatrix.setInventorySlotContents(G, k);
                }
            }
        }

        for (G = 0; G < event.craftMatrix.getSizeInventory(); ++G)
        {
            if (!event.craftMatrix.getStackInSlot(G) .isEmpty())
            {
                j = event.craftMatrix.getStackInSlot(G);

                if (j.getItem() == ItemIndex.diamondChisel)
                {
                    k = new ItemStack(ItemIndex.diamondChisel, 2, j.getItemDamage() + 1);

                    if (k.getItemDamage() >= k.getMaxDamage())
                    {
                        k.shrink(1);
                    }

                    event.craftMatrix.setInventorySlotContents(G, k);
                }
            }
        }

        for (G = 0; G < event.craftMatrix.getSizeInventory(); ++G)
        {
            if (!event.craftMatrix.getStackInSlot(G) .isEmpty())
            {
                j = event.craftMatrix.getStackInSlot(G);

                if (j.getItem() == ItemIndex.bloodstoneChisel)
                {
                    k = new ItemStack(ItemIndex.bloodstoneChisel, 2, j.getItemDamage() + 1);

                    if (k.getItemDamage() >= k.getMaxDamage())
                    {
                        k.shrink(1);
                    }

                    event.craftMatrix.setInventorySlotContents(G, k);
                }
            }
        }

        for (G = 0; G < event.craftMatrix.getSizeInventory(); ++G)
        {
            if (!event.craftMatrix.getStackInSlot(G).isEmpty())
            {
                j = event.craftMatrix.getStackInSlot(G);

                if (j.getItem() == ItemIndex.copperChisel)
                {
                    k = new ItemStack(ItemIndex.copperChisel, 2, j.getItemDamage() + 1);

                    if (k.getItemDamage() >= k.getMaxDamage())
                    {
                        k.shrink(1);
                    }

                    event.craftMatrix.setInventorySlotContents(G, k);
                }
            }
        }
        
        //Food Items
        for (G = 0; G < event.craftMatrix.getSizeInventory(); ++G)
        {
            if (!event.craftMatrix.getStackInSlot(G).isEmpty())
            {
                j = event.craftMatrix.getStackInSlot(G);

                if (j.getItem() == ItemIndex.churn)
                {
                    k = new ItemStack(ItemIndex.churn, 2, j.getItemDamage() + 1);

                    if (k.getItemDamage() >= k.getMaxDamage())
                    {
                        k.shrink(1);
                    }

                    event.craftMatrix.setInventorySlotContents(G, k);
                }
            }
        }
        
        //Currency Additions
        for (G = 0; G < event.craftMatrix.getSizeInventory(); ++G)
        {
            if (!event.craftMatrix.getStackInSlot(G) .isEmpty())
            {
                j = event.craftMatrix.getStackInSlot(G);

                if (j.getItem() == ItemIndex.coinMould)
                {
                    k = new ItemStack(ItemIndex.coinMould, 2, j.getItemDamage() + 1);

                    if (k.getItemDamage() >= k.getMaxDamage())
                    {
                        k.shrink(1);
                    }

                    event.craftMatrix.setInventorySlotContents(G, k);
                }
            }
        }
        for (G = 0; G < event.craftMatrix.getSizeInventory(); ++G)
        {
            if (!event.craftMatrix.getStackInSlot(G).isEmpty())
            {
                j = event.craftMatrix.getStackInSlot(G);

                if (j.getItem() == ItemIndex.ingotmould)
                {
                    k = new ItemStack(ItemIndex.ingotmould, 2, j.getItemDamage() + 1);

                    if (k.getItemDamage() >= k.getMaxDamage())
                    {
                        k.shrink(1);
                    }

                    event.craftMatrix.setInventorySlotContents(G, k);
                }
            }
        }
        
        //Hammers
        for (G = 0; G < event.craftMatrix.getSizeInventory(); ++G)
        {
            if (!event.craftMatrix.getStackInSlot(G).isEmpty())
            {
                j = event.craftMatrix.getStackInSlot(G);

                if (j.getItem() == ItemIndex.bloodstoneHammer)
                {
                    k = new ItemStack(ItemIndex.bloodstoneHammer, 2, j.getItemDamage() + 1);

                    if (k.getItemDamage() >= k.getMaxDamage())
                    {
                        k.shrink(1);
                    }

                    event.craftMatrix.setInventorySlotContents(G, k);
                    event.player.dropItem(irondust, true);
                }
            }
        }
        
        for (G = 0; G < event.craftMatrix.getSizeInventory(); ++G)
        {
            if (!event.craftMatrix.getStackInSlot(G) .isEmpty())
            {
                j = event.craftMatrix.getStackInSlot(G);

                if (j.getItem() == ItemIndex.hammer)
                {
                    k = new ItemStack(ItemIndex.hammer, 2, j.getItemDamage() + 1);

                    if (k.getItemDamage() >= k.getMaxDamage())
                    {
                        k.shrink(1);
                    }

                    event.craftMatrix.setInventorySlotContents(G, k);
                    event.player.dropItem(irondust, true);
                }
            }
        }
    }
}