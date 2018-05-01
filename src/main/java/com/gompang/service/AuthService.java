package com.gompang.service;

import com.gompang.domain.Player;
import com.gompang.domain.PlayerToken;
import com.gompang.packet.AuthResult;

public interface AuthService {
    PlayerToken login(Player player);
    boolean isLogin(Player player);
}
