package com.robocraft999.creategoggles.net;

import com.robocraft999.creategoggles.CreateGoggles;
import dev.architectury.networking.simple.MessageType;
import dev.architectury.networking.simple.SimpleNetworkManager;

public interface CGNet {
    SimpleNetworkManager NET = SimpleNetworkManager.create(CreateGoggles.MOD_ID);

    MessageType GOGGLE_SYNC = NET.registerS2C("sync_goggles", ClientboundEnableGogglesPacket::new);

    static void init(){

    }
}
