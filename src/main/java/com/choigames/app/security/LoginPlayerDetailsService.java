package com.choigames.app.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.choigames.app.entity.Player;
import com.choigames.app.repository.PlayerRepository;

@Service
public class LoginPlayerDetailsService implements UserDetailsService {

  private final PlayerRepository playerRepository;

  public LoginPlayerDetailsService(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  /**
   * emailによるプレイヤー検索
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    Player player = playerRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("プレイヤーが存在しません"));

    return new LoginPlayerDetails(player);
  }

}
