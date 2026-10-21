package org.cloudburstmc.protocol.bedrock.data;

import lombok.Value;

import java.util.UUID;

@Value
public class GenoaRewardSummaryItem {
    /**
     * The type of reward displayed on the end screen (ar_end_screen):
     * <ul>
     *   <li>0 - Currency / Rubies</li>
     *   <li>1 - Experience (XP)</li>
     *   <li>2 - Item reward (uses {@code itemUuid} and {@code amount})</li>
     *   <li>3 - Token / Activity reward</li>
     *   <li>4 - Bonus / Activity reward</li>
     *   <li>5 - Adventure completion reward</li>
     * </ul>
     */
    private long rewardType;
    private int amount;
    private UUID itemUuid;
}
