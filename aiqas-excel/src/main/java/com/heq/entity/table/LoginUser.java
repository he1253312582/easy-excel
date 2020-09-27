package com.heq.entity.table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.heq.enums.UserStatus;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
@Data
public class LoginUser extends SysUser implements UserDetails {

    /**
     * 返回授权操作
     *
     * @return
     */
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> menuSet = new HashSet<>();
        for (SysRole role : this.getRoleSet()) {
            for (SysMenu menu : role.getMenuSet()) {
                menuSet.add(new SimpleGrantedAuthority(menu.getMenuName()));
            }
        }
        return menuSet;
    }

    /**
     * 获取用户密码
     *
     * @return
     */
    @Override
    public String getPassword() {
        return super.getPassWord();
    }
    /**
     * 获取用户名
     */
    @Override
    public String getUsername() {
        return super.getUserName();
    }
    /**
     * 指示用户的帐户是否已过期。过期的帐户无法进行身份验证。
     *
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否被锁定
     *
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
       // return this.getAccountStatus() == UserStatus.USER_LOCK;
        return true;
    }

    /**
     * 指示用户的凭据（密码）是否已过期。过期的*凭据会阻止身份验证。
     *
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账号是否启用
     *
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return this.getAccountStatus() == UserStatus.USER_START;
    }
}
