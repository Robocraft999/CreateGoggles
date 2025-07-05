package com.robocraft999.creategoggles.net;

import com.robocraft999.creategoggles.CGConfig;
import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseS2CMessage;
import dev.architectury.networking.simple.MessageType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;

public class ClientboundEnableGogglesPacket extends BaseS2CMessage {

    boolean newState;

    public ClientboundEnableGogglesPacket(boolean newState){
        this.newState = newState;
    }

    public ClientboundEnableGogglesPacket(FriendlyByteBuf friendlyByteBuf) {
        newState = friendlyByteBuf.readBoolean();
    }

    @Override
    public MessageType getType() {
        return CGNet.GOGGLE_SYNC;
    }

    @Override
    public void write(RegistryFriendlyByteBuf buf) {
        buf.writeBoolean(newState);
    }

    @Override
    public void handle(NetworkManager.PacketContext context) {
        CGConfig.Common.enableGoggles.set(newState);
    }
}
