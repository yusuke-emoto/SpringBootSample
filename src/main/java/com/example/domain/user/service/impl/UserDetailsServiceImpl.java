package com.example.domain.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//loadUserByUsername()メソッドはユーザー名に基づいてユーザーを見つける

        // ユーザー情報取得
        MUser loginUser = service.getLoginUser(username);//UserServiceのgetLoginUserメソッドでユーザー情報を取得する

        // ユーザーが存在しない場合
        if(loginUser == null) {
            throw new UsernameNotFoundException("user not found");//ユーザーが存在しない場合、"user not found"というメッセージを使ってUsernameNotFoundExceptionという例外を返す
        }

        // 権限List作成
        GrantedAuthority authority = new SimpleGrantedAuthority(loginUser.getRole());//権限情報はGrantedAuthorityにセットする必要がある。SimpleGrantedAuthorityはGrantedAuthorityインターフェースの実装クラスで引数に権限情報の文字列を指定してインスタンスを生成している
        List<GrantedAuthority> authorities = new ArrayList<>();//GrantedAuthority型のArrayListを作成
        authorities.add(authority);//変数authorityに代入した権限情報をListに加える

        // UserDetails生成
        UserDetails userDetails = (UserDetails) new User(loginUser.getUserId(), loginUser.getPassword(), authorities);//loginUser(中身はgetLoginUserメソッドで取得したユーザー情報）のIDとパスワードと権限をUserDetails型に変換して代入

        return userDetails;
    }
}
