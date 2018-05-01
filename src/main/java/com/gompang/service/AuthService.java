package com.gompang.service;

import com.gompang.domain.Player;
import com.gompang.domain.PlayerToken;

public interface AuthService {
    PlayerToken login(Player player);
}
