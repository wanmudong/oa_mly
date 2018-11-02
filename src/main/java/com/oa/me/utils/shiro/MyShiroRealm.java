package com.oa.me.utils.shiro;

import com.oa.me.Dao.RoleDao;
import com.oa.me.Dao.UserDao;
import com.oa.me.Entity.SysPermission;
import com.oa.me.Entity.SysRole;
import com.oa.me.Entity.SysUser;
import com.oa.me.Service.UserService;
import com.oa.me.domain.User;
import com.oa.me.utils.SpringContextHolder;
import com.oa.me.utils.shiro.tool.CustomCredentialsMatcher;
import com.oa.me.utils.shiro.tool.IShiro;
import com.oa.me.utils.shiro.tool.RealmKit;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

import static com.oa.me.utils.oa_md5.md5;
import static com.oa.me.utils.oa_md5.md5_salt;

/**
 * Created by chenjiehao on 2018/9/21
 */


public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserDao userDao;
    @Resource
    private RoleDao roleDao;


    /**
     * 设定Password校验.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        //该句作用是重写shiro的密码验证，让shiro用我自己的验证
        setCredentialsMatcher(new CustomCredentialsMatcher());

    }



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {

        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
       UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String    username = token.getUsername();
        System.out.println(username);
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        IShiro shiro = RealmKit.me();
        SysUser sysUser = shiro.findSysUserByUsername(username);


        System.out.println("----->>sysUser="+sysUser);
        if(sysUser == null){
            return null;
        }
        System.out.println(sysUser);
        String salt = sysUser.getSalt();
        ByteSource credentialsSalt = new Md5Hash(salt);

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                sysUser, //用户名
                sysUser.getPassword(), //密码
//                credentialsSalt,
                getName()  //realm name
        );
        return authenticationInfo;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        SysUser sysUser = new SysUser();
        sysUser  = (SysUser)principals.getPrimaryPrincipal();


        IShiro shiro = RealmKit.me();
//      sysUser = shiro.getSysuser("2016214224");
        SysRole role = shiro.getSysUserRoleList(sysUser.getUsername());
        System.out.println(sysUser);
        authorizationInfo.addRole(role.getName());
            for(SysPermission p:shiro.getPermissions(role)){
                authorizationInfo.addStringPermission(p.getPerms());
            }
        return authorizationInfo;
    }
}
