package org.cloudburstmc.protocol.bedrock.data;

import lombok.Value;

import java.util.UUID;

@Value
public class GenoaRewardedItem {
    private UUID itemUuid;
    private long count;
}
