package com.choigames.app.service;

import java.time.Clock;
import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.choigames.app.entity.User;
import com.choigames.app.exception.DuplicateEmailException;
import com.choigames.app.form.SignupForm;
import com.choigames.app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignupService {

  private final UserRepository userRepository;

  private final Clock clock;

  /**
   * サインアップ画面より入力されたユーザー情報を基に
   * アカウント登録を行う
   * 
   * @param form
   */
  public void registerAccount(SignupForm form) {

    // メールアドレスの重複チェック
    if (userRepository.existsByEmail(form.getEmail())) {
      throw new DuplicateEmailException();
    }

    // パスワードエンコーダー
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // 登録エンティティ設定
    User user = new User();
    user.setEmail(form.getEmail());
    user.setPassword(encoder.encode(form.getPassword()));
    user.setUserName(form.getUserName());
    user.setCreatedAt(LocalDateTime.now(clock));
    user.setUpdatedAt(LocalDateTime.now(clock));

    // DB登録
    userRepository.save(user);
  }

}
