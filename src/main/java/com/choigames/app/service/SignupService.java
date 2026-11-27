package com.choigames.app.service;

import java.time.Clock;
import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.choigames.app.entity.Player;
import com.choigames.app.exception.DuplicateEmailException;
import com.choigames.app.form.SignupForm;
import com.choigames.app.repository.PlayerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignupService {

  private final PlayerRepository playerRepository;

  private final Clock clock;

  /**
   * サインアップ画面より入力されたユーザー情報を基に
   * アカウント登録を行う
   * 
   * @param form
   */
  public void registerAccount(SignupForm form) {

    // メールアドレスの重複チェック
    if (playerRepository.existsByEmail(form.getEmail())) {
      throw new DuplicateEmailException();
    }

    // パスワードエンコーダー
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // 登録エンティティ設定
    Player player = new Player();
    player.setEmail(form.getEmail());
    player.setPassword(encoder.encode(form.getPassword()));
    player.setPlayerName(form.getPlayerName());
    player.setCreatedAt(LocalDateTime.now(clock));
    player.setUpdatedAt(LocalDateTime.now(clock));

    // DB登録
    playerRepository.save(player);
  }

}
