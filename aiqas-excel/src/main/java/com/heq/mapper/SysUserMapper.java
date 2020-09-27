package com.heq.mapper;

import com.heq.entity.table.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysUserMapper {

    public List<SysUser> selectSysUsers(SysUser user);
    public SysUser selectSysUserByName(String userName);
    public SysUser selectSysUserById(String id);
    public List<SysUser> selectUserRoleAndMenu(SysUser user);
    public SysUser selectUserRoleAndMenuByUserName(String userName);




}
