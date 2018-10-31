package com.shanejim.myweb.personaladmin.security;

import com.shanejim.myweb.personalmodel.utils.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-31 15:48
 **/
@Component
public class SelfAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    MyUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = (String) authentication.getPrincipal(); // 这个获取表单输入中返回的用户名;
        String password = (String) authentication.getCredentials(); // 这个是表单中输入的密码；

//        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
//        String encodePwd = md5PasswordEncoder.encodePassword(password, userName);


        SaltedUser userInfo = (SaltedUser)userDetailsService.loadUserByUsername(userName);


        String encodePwd = DigestUtil.sha256Digest(password + userInfo.getSalt());

        if (!userInfo.getPassword().equals(encodePwd)) {
            throw new BadCredentialsException("用户名或密码错误！");
        }

        return new UsernamePasswordAuthenticationToken(userName, password, userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }


}
