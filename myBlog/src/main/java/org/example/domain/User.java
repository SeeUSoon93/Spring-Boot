package org.example.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails { // UserDetails를 상속받아 인증 객체로 사용

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    // OAuth 사용자 이름
    @Column(name = "nickname", unique = true)
    private String nickname;

    @Builder
    public User(String email, String password, String nickname){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public User update(String nickname){
        this.nickname = nickname;
        return this;
    }

    @Override //권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override //사용자 id반환
    public String getUsername(){
        return email;
    }

    @Override //사용자 패스워드
    public String getPassword(){
        return password;
    }

    @Override // 계정 만료 여부
    public boolean isAccountNonExpired(){
        // 여기에 만료되었는지 확인하는 로직
        return true;
    }

    @Override //계정 잠금 여부
    public boolean isAccountNonLocked(){
        // 계정 잠겼는지 로직
        return true;
    }

    @Override // 패스워드 만료 여부
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override //계정 사용가능 여부
    public boolean isEnabled(){
        return true;
    }

}
