package org.cloudburstmc.protocol.bedrock.codec.v425_genoa.serializer;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketSerializer;
import org.cloudburstmc.protocol.bedrock.data.GenoaBlockDropReward;
import org.cloudburstmc.protocol.bedrock.data.GenoaRewardSummaryItem;
import org.cloudburstmc.protocol.bedrock.data.GenoaRewardedItem;
import org.cloudburstmc.protocol.bedrock.packet.GenoaDisconnectIdPacket;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GenoaDisconnectIdSerializer_v425_Genoa implements BedrockPacketSerializer<GenoaDisconnectIdPacket> {
    public static final GenoaDisconnectIdSerializer_v425_Genoa INSTANCE = new GenoaDisconnectIdSerializer_v425_Genoa();

    // TODO: This packet is garbage, but this structure should parse it at least somewhat correctly.

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, GenoaDisconnectIdPacket packet) {
        this.writeStreamIds(buffer, packet);

        helper.writeArray(buffer, packet.getRewardSummary(), ByteBuf::writeIntLE, this::writeRewardSummaryItem);
        helper.writeArray(buffer, packet.getRewardedItems(), this::writeRewardedItem);

        buffer.writeIntLE((int) packet.getPlayerRuntimeId());
        helper.writeArray(buffer, packet.getRewardedTokens(), helper::writeUuid);
        helper.writeArray(buffer, packet.getBrokenBlocks(), helper::writeString);

        buffer.writeIntLE((int) packet.getUInt2());
        helper.writeArray(buffer, packet.getBlockDropRewards(), this::writeBlockDropReward);

        buffer.writeIntLE(packet.getSInt1());
        buffer.writeBoolean(packet.isHasDied());
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, GenoaDisconnectIdPacket packet) {
        this.readStreamIds(buffer, packet);

        helper.readArray(buffer, packet.getRewardSummary(), ByteBuf::readIntLE, this::readRewardSummaryItem);
        helper.readArray(buffer, packet.getRewardedItems(), this::readRewardedItem);

        packet.setPlayerRuntimeId(buffer.readUnsignedIntLE());
        helper.readArray(buffer, packet.getRewardedTokens(), helper::readUuid);
        helper.readArray(buffer, packet.getBrokenBlocks(), helper::readString);

        packet.setUInt2(buffer.readUnsignedIntLE());
        helper.readArray(buffer, packet.getBlockDropRewards(), this::readBlockDropReward);

        packet.setSInt1(buffer.readIntLE());
        packet.setHasDied(buffer.readBoolean());
    }

    protected GenoaRewardSummaryItem readRewardSummaryItem(ByteBuf buffer, BedrockCodecHelper helper) {
        return new GenoaRewardSummaryItem(buffer.readUnsignedIntLE(), buffer.readIntLE(), helper.readUuid(buffer));
    }

    protected void writeRewardSummaryItem(ByteBuf buffer, BedrockCodecHelper helper, GenoaRewardSummaryItem item) {
        buffer.writeIntLE((int) item.getRewardType());
        buffer.writeIntLE(item.getAmount());
        helper.writeUuid(buffer, item.getItemUuid());
    }

    protected GenoaRewardedItem readRewardedItem(ByteBuf buffer, BedrockCodecHelper helper) {
        return new GenoaRewardedItem(helper.readUuid(buffer), buffer.readUnsignedIntLE());
    }

    protected void writeRewardedItem(ByteBuf buffer, BedrockCodecHelper helper, GenoaRewardedItem item) {
        helper.writeUuid(buffer, item.getItemUuid());
        buffer.writeIntLE((int) item.getCount());
    }

    protected GenoaBlockDropReward readBlockDropReward(ByteBuf buffer, BedrockCodecHelper helper) {
        return new GenoaBlockDropReward(helper.readUuid(buffer), buffer.readIntLE());
    }

    protected void writeBlockDropReward(ByteBuf buffer, BedrockCodecHelper helper, GenoaBlockDropReward item) {
        helper.writeUuid(buffer, item.getItemUuid());
        buffer.writeIntLE(item.getCount());
    }

    protected void readStreamIds(ByteBuf buffer, GenoaDisconnectIdPacket packet) {
        if (buffer.readBoolean()) {
            packet.setBoostStreamVersion(buffer.readLongLE());
        }

        if (buffer.readBoolean()) {
            packet.setBuildplatesStreamVersion(buffer.readLongLE());
        }

        if (buffer.readBoolean()) {
            packet.setChallengesStreamVersion(buffer.readLongLE());
        }

        if (buffer.readBoolean()) {
            packet.setProfilesStreamVersion(buffer.readLongLE());
        }

        if (buffer.readBoolean()) {
            packet.setCraftingStreamVersion(buffer.readLongLE());
        }

        if (buffer.readBoolean()) {
            packet.setInventoryStreamVersion(buffer.readLongLE());
        }

        if (buffer.readBoolean()) {
            packet.setSmeltingStreamVersion(buffer.readLongLE());
        }

        if (buffer.readBoolean()) {
            packet.setTokensStreamVersion(buffer.readLongLE());
        }
    }

    protected void writeStreamIds(ByteBuf buffer, GenoaDisconnectIdPacket packet) {
        buffer.writeBoolean(packet.getBoostStreamVersion() != 0);
        if (packet.getBoostStreamVersion() != 0) {
            buffer.writeLongLE(packet.getBoostStreamVersion());
        }

        buffer.writeBoolean(packet.getBuildplatesStreamVersion() != 0);
        if (packet.getBuildplatesStreamVersion() != 0) {
            buffer.writeLongLE(packet.getBuildplatesStreamVersion());
        }

        buffer.writeBoolean(packet.getChallengesStreamVersion() != 0);
        if (packet.getChallengesStreamVersion() != 0) {
            buffer.writeLongLE(packet.getChallengesStreamVersion());
        }

        buffer.writeBoolean(packet.getProfilesStreamVersion() != 0);
        if (packet.getProfilesStreamVersion() != 0) {
            buffer.writeLongLE(packet.getProfilesStreamVersion());
        }

        buffer.writeBoolean(packet.getCraftingStreamVersion() != 0);
        if (packet.getCraftingStreamVersion() != 0) {
            buffer.writeLongLE(packet.getCraftingStreamVersion());
        }

        buffer.writeBoolean(packet.getInventoryStreamVersion() != 0);
        if (packet.getInventoryStreamVersion() != 0) {
            buffer.writeLongLE(packet.getInventoryStreamVersion());
        }

        buffer.writeBoolean(packet.getSmeltingStreamVersion() != 0);
        if (packet.getSmeltingStreamVersion() != 0) {
            buffer.writeLongLE(packet.getSmeltingStreamVersion());
        }

        buffer.writeBoolean(packet.getTokensStreamVersion() != 0);
        if (packet.getTokensStreamVersion() != 0) {
            buffer.writeLongLE(packet.getTokensStreamVersion());
        }
    }
}
