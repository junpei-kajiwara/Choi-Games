package com.choigames.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  /**
   * パスワードエンコーダー
   * 
   * @return
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http

        // H2コンソール用
        .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))

        // H2コンソールのフレーム表示を許可
        .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))

        // URLごとのアクセス制御
        .authorizeHttpRequests(auth -> auth

            // ログイン画面
            .requestMatchers("/login").permitAll()

            // H2コンソール
            .requestMatchers("/h2-console/**").permitAll()

            // 静的ファイル
            .requestMatchers("/css/**", "/js/**", "/img/**", "/audio/**").permitAll()

            // その他はログイン必須
            .anyRequest().authenticated())

        // ログイン設定
        .formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login").usernameParameter("email")
            .passwordParameter("password").defaultSuccessUrl("/home", true).failureUrl("/login?error").permitAll())

        // ログアウト設定
        .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout").invalidateHttpSession(true)
            .deleteCookies("JSESSIONID").permitAll());

    return http.build();
  }

}
