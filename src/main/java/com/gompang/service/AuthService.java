package com.gompang.service;

import com.gompang.domain.Player;
import com.gompang.packet.AuthResult;

public interface AuthService {
    short login(Player player);
    boolean isLogin(Player player);
}
