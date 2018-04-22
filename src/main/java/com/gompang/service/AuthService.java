package com.gompang.service;

import com.gompang.common.domain.LoginResult;
import com.gompang.domain.Player;

public interface AuthService {
    LoginResult login(Player player);
    boolean isLogin(Player player);
}
