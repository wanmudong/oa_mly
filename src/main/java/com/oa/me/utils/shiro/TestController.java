package com.oa.me.utils.shiro;

import com.oa.me.Dao.DepartDao;
import com.oa.me.Dao.RoleDao;
import com.oa.me.Dao.UserDao;
import com.oa.me.Entity.SysPermission;
import com.oa.me.Entity.SysRole;
import com.oa.me.Entity.SysUser;
import com.oa.me.domain.User;
import com.sun.deploy.net.HttpResponse;
import org.apache.ibatis.annotations.Delete;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by chenjiehao on 2018/9/21
 */
@RestController
@EnableAutoConfiguration
public class TestController {
    @Resource
    private UserDao userDao;
    @Resource
    private RoleDao roleDao;

    @Resource
    private DepartDao departDao;

    @GetMapping("test")
    private int test(){
       new MyShiroRealm().doGetAuthorizationInfo(new PrincipalCollection() {
           @Override
           public Iterator iterator() {
               return null;
           }

           @Override
           public Object getPrimaryPrincipal() {
               return null;
           }

           @Override
           public <T> T oneByType(Class<T> aClass) {
               return null;
           }

           @Override
           public <T> Collection<T> byType(Class<T> aClass) {
               return null;
           }

           @Override
           public List asList() {
               return null;
           }

           @Override
           public Set asSet() {
               return null;
           }

           @Override
           public Collection fromRealm(String s) {
               return null;
           }

           @Override
           public Set<String> getRealmNames() {
               return null;
           }

           @Override
           public boolean isEmpty() {
               return false;
           }
       });
       return 1;
    }
    @GetMapping("test1")
    public String test1(){
        SimpleAuthenticationInfo info = (SimpleAuthenticationInfo) new MyShiroRealm().getAuthenticationInfo(new AuthenticationToken() {
            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }
        });
        return info.toString();
    }


    @GetMapping("test2")
    public String test2(){
        SysUser sysUser = new SysUser();
//         sysUser  = (SysUser)principals.getPrimaryPrincipal();
        /**
         * 测试写死
         */
//        sysUser.setUsername("2016214224");
        sysUser = userDao.findSysUserByUsername("2016214224");
        SysRole role = userDao.getSysUserRoleList("2016214224");
//        authorizationInfo.addRole(role.getName());
        System.out.println(role);
        for(SysPermission p:roleDao.getPermissions(role.getId())){
//            authorizationInfo.addStringPermission(p.getPerms());
            System.out.println(p);
        }
        return sysUser.toString();
    }
    @GetMapping("test4")
    public Long isLogin(){
        Long currentUserId = (Long) SecurityUtils.getSubject().getSession().getAttribute("currentUserId");
        return currentUserId;
    }
    @GetMapping("test5")
    public List<User> test5(String any){
        return userDao.getUserByAny(any);
    }
    @GetMapping("test6")
    public void  test6(HttpServletResponse response) throws IOException {

        List<User> queryAll = userDao.getAllUser();
        //工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("测试表");
        //设置导出的表的名字
        String fileName = "memberInfo.xls";
        //设置头

        String[] headers = {"部门", "姓名", "学号", "性别", "学院", "专业", "qq号", "手机号码", "银行卡号"};
        HSSFRow row = sheet.createRow(0);
        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);

        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            cell.setCellStyle(style);

        }

        int rowNum = 1;
        HSSFRow row1;
        for (User user : queryAll) {
            System.out.println(user);

             row1 = sheet.createRow(rowNum);
            System.out.println(rowNum);
            row1.createCell(0).setCellValue(departDao.getNameById(user.getDepart()));
            row1.createCell(1).setCellValue(user.getName());
            row1.createCell(2).setCellValue(String.valueOf(user.getStuid()));
            row1.createCell(3).setCellValue(user.getSex());
            row1.createCell(4).setCellValue(user.getCollege());
            row1.createCell(5).setCellValue(user.getMajor());
            row1.createCell(6).setCellValue(user.getQq());
            row1.createCell(7).setCellValue(user.getPhone());
            row1.createCell(8).setCellValue(user.getDebitcard());


//            HSSFCell createCell = row1.createCell(9);
//            createCell.setCellValue(new Date());
//            createCell.setCellStyle(style);
            rowNum++;
       }
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
            response.flushBuffer();
            workbook.write(response.getOutputStream());
            workbook.close();


        }

}
