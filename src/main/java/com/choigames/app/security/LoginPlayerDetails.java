package com.choigames.app.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.choigames.app.entity.Player;

public class LoginPlayerDetails implements UserDetails {

  private final Long playerId;

  private final String playerName;

  private final String email;

  private final String password;

  private final Collection<? extends GrantedAuthority> authorities;

  public LoginPlayerDetails(Player player) {
    this.playerId = player.getPlayerId();
    this.playerName = player.getPlayerName();
    this.email = player.getEmail();
    this.password = player.getPassword();
    this.authorities = null; // 使用しない
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public Long getPlayerId() {
    return playerId;
  }

  public String getPlayerName() {
    return playerName;
  }

  public String getEmail() {
    return email;
  }

}
