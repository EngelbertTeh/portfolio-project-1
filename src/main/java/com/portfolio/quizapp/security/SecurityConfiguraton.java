package com.portfolio.quizapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguraton {



  @Bean
  public UserDetailsService userDetailsService(PasswordEncoder encoder) {
 
    UserDetails admin = User
      .withUsername("admin")
      .password(encoder.encode("admin"))
      .roles("ADMIN")
      .build();

      UserDetails user = User
      .withUsername("user")
      .password(encoder.encode("user"))
      .roles("USER")
      .build();
    return new InMemoryUserDetailsManager(user,admin);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
     httpSecurity
     .csrf(
      (csrf) -> csrf
      .disable()
    )
    .authorizeHttpRequests((authorize)->{ 
      authorize
      .requestMatchers("/question/allQuestions")
      .permitAll()
      .requestMatchers(HttpMethod.POST,"/question/add")
      .hasRole("ADMIN")
      .requestMatchers(HttpMethod.PUT,"/question/update")
      .hasRole("ADMIN")
      .requestMatchers(HttpMethod.DELETE,"/question/delete/{id}")
      .hasRole("ADMIN")
      .anyRequest().authenticated();
      })
    .httpBasic(Customizer.withDefaults())
    .formLogin(Customizer.withDefaults());
    

   
    
    return httpSecurity.build();
  }
}
