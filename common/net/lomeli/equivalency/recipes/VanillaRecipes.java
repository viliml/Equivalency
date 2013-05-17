package net.lomeli.equivalency.recipes;

import net.lomeli.equivalency.helper.TransmutationHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class VanillaRecipes 
{
	public static final int WILDCARD = Short.MAX_VALUE;
	
	public static void loadRecipes(ItemStack transmutationStone)
	{
		// 8 Glass Panes -> 3 Glass Blocks
		TransmutationHelper.addRecipe((new ItemStack(Block.glass, 3)), transmutationStone,
			new Object[]{Block.thinGlass, Block.thinGlass, Block.thinGlass,
			Block.thinGlass, Block.thinGlass, Block.thinGlass, Block.thinGlass,
			Block.thinGlass});
		
		// 1 Coal -> 2 RedStone
		TransmutationHelper.addRecipe(Item.coal, transmutationStone,
			new Object[]{Item.redstone, Item.redstone});
		// 2 RedStone -> 1 Coal
		TransmutationHelper.addRecipe(new ItemStack(Item.redstone, 2), transmutationStone,
			new Object[]{(new ItemStack(Item.coal, 1, WILDCARD))});
		
		// 7 Coal -> 1 Lapis
		TransmutationHelper.addRecipe((new ItemStack(Item.dyePowder, 1, 4)), transmutationStone,
			new Object[] { (new ItemStack(Item.coal, 1, WILDCARD)), (new ItemStack(Item.coal, 1, WILDCARD)), 
			(new ItemStack(Item.coal, 1, WILDCARD)), (new ItemStack(Item.coal, 1, WILDCARD)), 
			(new ItemStack(Item.coal, 1, WILDCARD)), (new ItemStack(Item.coal, 1, WILDCARD)),
			(new ItemStack(Item.coal, 1, WILDCARD)), (new ItemStack(Item.coal, 1, WILDCARD))});
		// 1 Lapis -> 7 Coal
		TransmutationHelper.addRecipe(new ItemStack(Item.coal, 8), transmutationStone, 
			new Object[]{(new ItemStack(Item.dyePowder, 1, 4))});
		
		// 8 Gold Ingot -> 1 Emerald
		TransmutationHelper.addRecipe(Item.emerald, transmutationStone, new Object[]
			{ Item.ingotGold, Item.ingotGold, Item.ingotGold, Item.ingotGold,
			Item.ingotGold, Item.ingotGold, Item.ingotGold, Item.ingotGold });
		// 1 Emerald -> 8 Gold Ingot
		TransmutationHelper.addRecipe(new ItemStack(Item.ingotGold, 8), 
			transmutationStone, new Object[]{Item.emerald});
		
		// 1 Redstone Block -> 1 Glowstone dust
		TransmutationHelper.addRecipe(Item.lightStoneDust, transmutationStone, new Object[]
			{ Block.redstoneWire });
		
		// 1 Glowstone dust -> 1 Redstone Block
		TransmutationHelper.addRecipe(Block.redstoneWire, transmutationStone, new Object[]
			{ Item.lightStoneDust });
		
		// 1 Glowstone block -> 36 redstone dust
		TransmutationHelper.addRecipe(new ItemStack(Item.redstone, 36), transmutationStone, 
			new Object[]{ Block.glowStone });
			
		// 5 Blaze rods -> 1 Diamond Disabled due to exploit
		//TransmutationHelper.addRecipe(Item.diamond, transmutationStone, new Object[]{
		//	Item.blazeRod, Item.blazeRod, Item.blazeRod, Item.blazeRod, Item.blazeRod});
	}
}
