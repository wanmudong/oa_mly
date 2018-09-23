package com.oa.me.utils.shiro.tool;

import com.oa.me.Entity.SysUser;
import com.oa.me.utils.oa_md5;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Sha384Hash;

import static org.apache.poi.poifs.crypt.HashAlgorithm.md5;


/**
 * 自定义 密码验证类
 * @author q
 *
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        SysUser sysUser = (SysUser) info.getPrincipals().getPrimaryPrincipal();

//       org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
//       SysUser sysUser = (SysUser) subject.getPrincipal();
        String salt = sysUser.getSalt();

        Object tokenCredentials = encrypt(String.valueOf(token.getPassword()),salt);
        Object accountCredentials = getCredentials(info);
        //将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false
        return equals(tokenCredentials, accountCredentials);
    }

    //将传进来密码加密方法
    private String encrypt(String data,String salt) {
//        String sha384Hex = new Sha384Hash(data).toBase64();//这里可以选择自己的密码验证方式 比如 md5或者sha256等
        String sha384Hex = oa_md5.md5_salt(data,salt);
        return sha384Hex;
    }
}
