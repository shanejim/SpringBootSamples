package com.shanejim.myweb.personaladmin.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-30 16:46
 **/
@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("用户的用户名: {}", username);
        // TODO 根据用户名，去数据库查找到对应的密码，与权限



        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("admintest");
        grantedAuthorities.add(grantedAuthority);


        // 封装用户信息，并返回。参数分别是：用户名，密码（数据库里面的），用户权限
        User user = new User(username, "123456", grantedAuthorities);
        //User user = new User(username, "123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin2"));

//        user.isCredentialsNonExpired()

        return user;
    }
}
