package com.choigames.app.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.choigames.app.entity.Player;
import com.choigames.app.repository.PlayerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final PlayerRepository playerRepository;

  public CustomUserDetailsService(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  /**
   * emailによるプレイヤー検索
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    Player player = playerRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("プレイヤーが存在しません"));

    return User.withUsername(player.getEmail()).password(player.getPassword()).build();
  }

}
