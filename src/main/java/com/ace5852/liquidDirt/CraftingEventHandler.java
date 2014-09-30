package com.ace5852.liquidDirt;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class CraftingEventHandler {
	final ItemStack mud = new ItemStack(LiquidDirt.mudBucket);
	final ItemStack water = new ItemStack(Items.potionitem);

	@SubscribeEvent
	public void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
		if (event.crafting.isItemEqual(mud)) {
			for(int i = 0, invSize = event.craftMatrix.getSizeInventory(); i < invSize; i++) {
				ItemStack slotStack = event.craftMatrix.getStackInSlot(i);
				if (slotStack != null && slotStack.isItemEqual(water)) {
					ItemStack bottle = new ItemStack(Items.glass_bottle);
					if (!event.player.inventory.addItemStackToInventory(bottle)) {
						event.player.entityDropItem(bottle, 0.5F);
					}
				}
			}
		}
	}
}
