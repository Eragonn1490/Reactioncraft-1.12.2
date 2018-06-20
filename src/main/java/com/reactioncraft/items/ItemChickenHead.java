package com.reactioncraft.items;

public class ItemChickenHead extends ItemFoodBase
{
	public ItemChickenHead(String name, int amount, float saturation, boolean isWolfFood)
	{
		super(name, amount, saturation, isWolfFood);
		{
			this.setMaxStackSize(1);
		}
	}
}