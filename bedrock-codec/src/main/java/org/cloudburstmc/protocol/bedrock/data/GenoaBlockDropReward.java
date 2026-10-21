package org.cloudburstmc.protocol.bedrock.data;

import lombok.Value;

import java.util.UUID;

@Value
public class GenoaBlockDropReward {
    private UUID itemUuid;
    private int count;
}
