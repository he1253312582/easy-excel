package com.heq.service;

import com.heq.entity.table.LoginUser;
import com.heq.entity.table.SysUser;
import com.heq.enums.UserStatus;
import com.heq.mapper.SysUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String uName) throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.selectUserRoleAndMenuByUserName(uName);
        if (sysUser == null) {
            throw new AuthenticationCredentialsNotFoundException("用户名不存在!");
        } else if (sysUser.getAccountStatus() == UserStatus.USER_STOP) {
            throw new LockedException("用户被禁用!");
        } else if (sysUser.getAccountStatus() == UserStatus.USER_LOCK) {
            throw new LockedException("用户被锁定!");
        }

        /**
         * BadCredentialsException  错误的凭证例外
         * CredentialsExpiredException  凭证过期异常
         * DisabledException  禁用的异常
         */
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(sysUser,loginUser);
        return loginUser;
    }
}
