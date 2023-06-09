package com.robocraft999.creategoggles.item.backtank;

import com.robocraft999.creategoggles.registry.CGTileEntities;
import com.simibubi.create.content.equipment.armor.BacktankBlock;
import com.simibubi.create.content.equipment.armor.BacktankBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class CGBacktankBlock extends BacktankBlock {

    public CGBacktankBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends BacktankBlockEntity> getBlockEntityType() {
        return CGTileEntities.BACKTANK.get();
    }
}
