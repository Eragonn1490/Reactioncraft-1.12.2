/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.multiblock;

import forestry.api.core.ICamouflageHandler;
import forestry.api.greenhouse.IGreenhouseHousing;
import net.minecraft.util.math.BlockPos;

public interface IGreenhouseController extends IMultiblockController, IGreenhouseHousing, ICamouflageHandler {
	
	/**
	 *
	 * @return the position of the block at the top of the greenhouse structure that shows the state of the greenhouse.
	 */
	BlockPos getCenterCoordinates();

	void setCenterCoordinates(BlockPos coordinates);
	
}
