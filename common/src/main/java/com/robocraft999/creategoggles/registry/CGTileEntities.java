package com.robocraft999.creategoggles.registry;

import com.robocraft999.creategoggles.CreateGoggles;
import com.simibubi.create.content.equipment.armor.BacktankBlockEntity;
import com.simibubi.create.content.equipment.armor.BacktankInstance;
import com.simibubi.create.content.equipment.armor.BacktankRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

import static com.robocraft999.creategoggles.CreateGoggles.REGISTRATE;

public class CGTileEntities {

    static{
        //TODO remove
        CreateGoggles.LOGGER.info("cgblockentities");
    }

    public static final BlockEntityEntry<BacktankBlockEntity> BACKTANK = REGISTRATE
            .blockEntity("cg_backtank", BacktankBlockEntity::new)
            .instance(() -> BacktankInstance::new)
            .validBlocks(
                    CGBlocks.CHAINMAIL_BACKTANK_BLOCK,
                    CGBlocks.DIAMOND_BACKTANK_BLOCK,
                    CGBlocks.GOLDEN_BACKTANK_BLOCK,
                    CGBlocks.IRON_BACKTANK_BLOCK,
                    CGBlocks.LEATHER_BACKTANK_BLOCK)
            .renderer(() -> BacktankRenderer::new)
            .register();

    public static void register() {}

}
