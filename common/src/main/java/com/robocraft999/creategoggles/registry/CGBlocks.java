package com.robocraft999.creategoggles.registry;

import com.robocraft999.creategoggles.CreateGoggles;
import com.robocraft999.creategoggles.item.backtank.CGBacktankBlock;
import com.simibubi.create.content.equipment.armor.BacktankBlock;
import com.simibubi.create.content.equipment.armor.BacktankItem;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;

import static com.robocraft999.creategoggles.CreateGoggles.REGISTRATE;

public class CGBlocks {

    static {
        //TODO remove
        CreateGoggles.LOGGER.info("cgblocks");
    }
    public static final BlockEntry<? extends BacktankBlock>
            CHAINMAIL_BACKTANK_BLOCK = backtankBlock("chainmail_backtank", CGItems.CHAINMAIL_BACKTANK),
            DIAMOND_BACKTANK_BLOCK = backtankBlock("diamond_backtank", CGItems.DIAMOND_BACKTANK),
            GOLDEN_BACKTANK_BLOCK = backtankBlock("golden_backtank", CGItems.GOLDEN_BACKTANK),
            IRON_BACKTANK_BLOCK = backtankBlock("iron_backtank", CGItems.IRON_BACKTANK),
            LEATHER_BACKTANK_BLOCK = backtankBlock("leather_backtank", CGItems.LEATHER_BACKTANK);
    private static BlockEntry<CGBacktankBlock> backtankBlock(String name, ItemEntry<? extends BacktankItem> type) {
        return REGISTRATE.block(name, CGBacktankBlock::new)
                .initialProperties(SharedProperties::copperMetal)
                .transform(BuilderTransformers.backtank(type::get))
                .register();
    }

    public static void register() {}
}
