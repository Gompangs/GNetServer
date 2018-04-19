package com.gompang.repository;

import com.gompang.domain.Player;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryPlayerStore implements PlayerStore {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ConcurrentHashMap<String, Player> playerStore;

    @PostConstruct
    public void init() {
        this.playerStore = new ConcurrentHashMap<>();
    }

    @Override
    public Player getPlayer(String playerId) {
        return this.playerStore.get(playerId);
    }

    @Override
    public boolean putPlayer(Player player) {
        Player existingPlayer = this.playerStore.get(player.getPlayerId());
        if (existingPlayer == null) {
            this.playerStore.put(player.getPlayerId(), player);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean putDeviceId(Player player) {
        Player existingPlayer = this.playerStore.get(player.getPlayerId());
        if (existingPlayer != null) {
            List<String> storedDeviceIds = existingPlayer.getStoredDeviceIds();
            storedDeviceIds.add(player.getDeviceId());
            this.playerStore.put(player.getPlayerId(), player);
            return true;
        } else {
            return false;
        }
    }
}
