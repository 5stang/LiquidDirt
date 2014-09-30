package com.ace5852.liquidDirt;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class CraftingEventHandler {
	final ItemStack mudBucket = new ItemStack(LiquidDirt.mudBucket);
	final ItemStack waterBottle = new ItemStack(Items.potionitem);
	final ItemStack waterBucket = new ItemStack(Items.water_bucket);

	@SubscribeEvent
	public void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
		if (event.crafting.isItemEqual(mudBucket)) {
			for(int i = 0, invSize = event.craftMatrix.getSizeInventory(); i < invSize; i++) {
				ItemStack slotStack = event.craftMatrix.getStackInSlot(i);
				if (slotStack != null) {
					if (LiquidDirt.enableCraftingWater && slotStack.isItemEqual(waterBottle)) {
						ItemStack bottle = new ItemStack(Items.glass_bottle);
						if (!event.player.inventory.addItemStackToInventory(bottle)) {
							event.player.entityDropItem(bottle, 0.5F);
						}
					} else if (slotStack.isItemEqual(waterBucket)) {
						slotStack.func_150996_a(Items.stick); // replace the item in the itemstack that was just used in the craft with something else, something consumable
					}
				}
			}
		}
	}
}
