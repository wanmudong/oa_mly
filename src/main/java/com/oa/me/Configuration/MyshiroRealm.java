//package com.oa.me.Configuration;
//
///**
// * Created by chenjiehao on 2018/9/18
// */
//
//import com.oa.me.Dao.UserDao;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//
//import javax.annotation.Resource;
//
///**
// *  身份校验核心类;
// * @author ZZ
// * @version v.0.1
// */
//public class MyShiroRealm extends AuthorizingRealm {
//
//
//
//
//    @Resource
//    private UserInfoService userInfoService;
//    @Resource
//    private UserDao userDao;
//    @Resource
//    private RoleDao roleDao;
//    @Resource
//    private UserRoleDao userRoleDao;
//    @Resource
//    private RolePermissionDao rolePermissionDao;
//    @Resource
//    private PermissionDao permissionDao;
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        String username = (String)token.getPrincipal();
//        //通过username从数据库中查找 User对象，如果找到，没找到.
//        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
//        userInfo userInfo = userInfoService.findByUsername(username);
//        System.out.println(userInfo);
//        System.out.println("----->>userInfo="+userInfo.getUsername()+"---"+userInfo.getPassword());
//        if(userInfo == null){
//            return null;
//        }
//
//
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                userInfo, //用户名
//                userInfo.getPassword(), //密码
//                getName()  //realm name
//        );
//
//        return authenticationInfo;
//    }
//
//
//
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//
//        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        userInfo userinfo  = (userInfo)principals.getPrimaryPrincipal();
////		userInfo userInfo= userInfoService.findByUsername(username);
//        int uid=userinfo.getId();
//        List<userrole> list=userRoleDao.getRoles(uid);
//        for(userrole userrole:list){
//            int rolid=userrole.getRoleid();
//            authorizationInfo.addRole(roleDao.getRole(rolid).getRole());
//            System.out.println(roleDao.getRole(rolid).getRole());
//            List<rolepermission> list1=rolePermissionDao.getPermissions(rolid);
//            for(rolepermission p:list1){
//
//                authorizationInfo.addStringPermission(permissionDao.getPermission(p.getPermissionid()).getPermission());
//                System.out.println(permissionDao.getPermission(p.getPermissionid()).getPermission());
//            }
//        }
//
//
//
//        return authorizationInfo;
//    }
//
//
//
//}
