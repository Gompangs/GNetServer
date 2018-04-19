package com.gompang.repository;

import com.gompang.domain.Player;

public interface PlayerStore {

    // player
    Player getPlayer(String playerId);
    boolean putPlayer(Player player);

    // device
    boolean putDeviceId(Player player);
}
