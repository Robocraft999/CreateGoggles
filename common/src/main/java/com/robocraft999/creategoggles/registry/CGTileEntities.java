package com.robocraft999.creategoggles.registry;

import com.simibubi.create.content.curiosities.armor.CopperBacktankInstance;
import com.simibubi.create.content.curiosities.armor.CopperBacktankRenderer;
import com.simibubi.create.content.curiosities.armor.CopperBacktankTileEntity;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.minecraft.core.Registry;

import static com.robocraft999.creategoggles.CreateGoggles.REGISTRATE;

public class CGTileEntities {

    public static final BlockEntityEntry<CopperBacktankTileEntity>
            CHAINMAIL_BACKTANK_TILE = backtankTile("chainmail_backtank"),
            DIAMOND_BACKTANK_TILE = backtankTile("diamond_backtank"),
            GOLDEN_BACKTANK_TILE = backtankTile("golden_backtank"),
            IRON_BACKTANK_TILE = backtankTile("iron_backtank"),
            NETHERITE_BACKTANK_TILE = backtankTile("netherite_backtank"),
            LEATHER_BACKTANK_TILE = backtankTile("leather_backtank");


    private static BlockEntityEntry<CopperBacktankTileEntity> backtankTile(String name){
        return REGISTRATE
                .tileEntity(name, CopperBacktankTileEntity::new)
                .instance(() -> CopperBacktankInstance::new)
                .validBlocks(REGISTRATE.get(name, Registry.BLOCK_REGISTRY))
                .renderer(() -> CopperBacktankRenderer::new)
                .register();
    }

    public static void register() {}

}
