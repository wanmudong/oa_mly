package com.oa.me.controller;


import com.oa.me.Dao.DepartDao;
import com.oa.me.Dao.UserDao;
import com.oa.me.Entity.Message_oa;
import com.oa.me.Entity.Result;
import com.oa.me.Entity.SysUser;
import com.oa.me.Service.DictService;
import com.oa.me.Service.UserService;
import com.oa.me.domain.*;
import com.oa.me.utils.LoginUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.oa.me.utils.oa_md5.md5;
import static com.oa.me.utils.timeUtil.getSecondTimeNow;

/**
 * Created by chenjiehao on 2018/9/17
 */
@Controller
@EnableAutoConfiguration
public class UserController {



    @Resource
    private UserService userService;

    @Resource
    private DictService dictService;
    @Resource
    private UserDao userDao;
    @Resource
    private DepartDao departDao;

    @GetMapping("/index")
    public String index(){
        System.out.println(SecurityUtils.getSubject().getPrincipal());
        return "index"; //当浏览器输入/index时，会返回 /templates/home.html页面
    }



    @RequestMapping("/userList")
    @RequiresPermissions("userInfo:view")//权限管理;
    public String userInfo(){
        return "userInfo";
    }

    //todo:改成post
    @RequestMapping("/api/login")
    @ResponseBody
    public Result<UserOa> login(
//            @RequestParam("username") String username, @RequestParam("password") String password
            UserModel userModel) throws Exception{


        Result result = new Result();
        Message_oa mo = new Message_oa();
        List<UserOa> list =new ArrayList<UserOa>();
        String username = userModel.getUsername();
        String password = userModel.getPassword();
        if(username == null||password == null)
        {
            mo.setText("用户名或密码为空");

            result.setSuccess(false);
            mo.setText("登陆失败");
            mo.setLogin(false);
            result.setMsg(mo);
            result.setData(list);
            return result;
        }


        System.out.println("HomeController.login()");
        Subject subject = SecurityUtils.getSubject() ;
        UsernamePasswordToken token = new UsernamePasswordToken(username,password) ;
        try {
            subject.login(token);
        }catch (UnknownAccountException uae) {
//            log.info("对用户[{}]进行登录验证..验证未通过,未知账户", username);
            result.getMsg().setText("未知账户");
            return result;
        } catch (IncorrectCredentialsException ice) {
//            log.info("对用户[{}]进行登录验证..验证未通过,错误的凭证", username);
            result.getMsg().setText("未知账户");
            return result;
        } catch (LockedAccountException lae) {
//            log.info("对用户[{}]进行登录验证..验证未通过,账户已锁定", username);
            result.getMsg().setText("未知账户");
            return result;
        } catch (ExcessiveAttemptsException eae) {
//            log.info("对用户[{}]进行登录验证..验证未通过,错误次数过多", username);
            result.getMsg().setText("未知账户");
            return result;
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
//            log.info("对用户[{}]进行登录验证..验证未通过,堆栈轨迹如下", username);
            result.getMsg().setText("未知账户");
            ae.printStackTrace();
            return result;
        }
        System.out.println("----------");
        System.out.println(SecurityUtils.getSubject().getPrincipal());
        System.out.println("----------");
        System.out.println(SecurityUtils.getSubject().getPrincipal());

        subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() !=null)
       {
            //登录成功
           UserOa userOa = userService.getUserLogin(userModel);
           list.add(userOa);
            result.setSuccess(true);

            mo.setText("登陆成功");
            mo.setLogin(true);
            result.setMsg(mo);

            result.setData(list);
            return result;

        }
        //登录失败的情况下
        result.setSuccess(false);
        mo.setText("登陆失败");
        mo.setLogin(false);
        result.setMsg(mo);
        result.setData(list);
        return result;

    }
    @GetMapping("/hello")
    public int hello(){

       String pwd =  md5(md5(md5("online666"))+"nrh575");
       int time = getSecondTimeNow();
        return time;
    }



    @GetMapping("/api/getAllUser")
    @ResponseBody
    public Result<User> getAllUser(){
        Result result = new Result();
        Message_oa mo = new Message_oa();
        List<User> list = userService.getAllUser();
        //TODO：权限以及登录管理
        if (list != null)
        {
            result.setData(list);
            mo.setText("获取成功！");

            result.setMsg(mo);
            result.setSuccess(true);
            return result;
        }
        mo.setText("获取失败！");
        result.setMsg(mo);
        result.setSuccess(true);

        return result;
     }
     @GetMapping("/api/getUserByStuid")
     @ResponseBody
     public Result getUserByStuid(){
         Result result = new Result();
         List list= new ArrayList();
         Message_oa mo = new Message_oa();
         SysUser sysuser = (SysUser) SecurityUtils.getSubject().getPrincipal();
         if (sysuser ==null)
         {
             //用户已注销
             result.setMsg(mo);
             result.getMsg().setText("用户已注销");
             return result;
         }
         String stuid = sysuser.getUsername();
//         Result result = new Result();
//         List list= new ArrayList();
//         Message_oa mo = new Message_oa();
         User user = userService.getUserByStuid(stuid);
         Map<String, List<Dict>> dict = dictService.getAllDict();

         Subject subject = SecurityUtils.getSubject();
         if (subject.getPrincipal() !=null)
         {
             mo.setLogin(true);
         }
         else {
             mo.setLogin(false);
         }
         if (user != null)
         {

             list.add(user);
             list.add(dict);
             result.setData(list);
             mo.setText("获取成功！");

             result.setMsg(mo);
             result.setSuccess(true);
             return result;
         }
         mo.setText("获取失败！");
         result.setMsg(mo);
         result.setSuccess(false);

        return result;
     }
    @GetMapping("/api/getUserByAny")
    @ResponseBody
    public Result getUserByAny(String any){
        Result result = new Result();
        Message_oa mo = new Message_oa();
        mo.setLogin(LoginUtil.isLogin());
        List<User> list =  userService.getUserByAll(any);
        if (!list.isEmpty())
        {
            result.setData(list);

            mo.setText("获取成功！");
            result.setMsg(mo);
            result.setSuccess(true);
            return result;
        }
        result.setData(list);
        mo.setText("获取失败！");
        result.setMsg(mo);
        result.setSuccess(false);
        return result;
    }

    @PostMapping("/api/updateMember")
    @ResponseBody
    public Result updateMember(User user) {
        Result result = new Result();
        Message_oa mo = new Message_oa();
        mo.setLogin(LoginUtil.isLogin());
        List<String> list  = new ArrayList<String>();
        boolean success=false;
        try {

        success =   userService.updateMember(user);
        }catch (Exception e){
            //todo:异常处理
        }
        if (success)
        {
            list.add("update");
            result.setData(list);

            mo.setText("更新成功！");
            result.setMsg(mo);
            result.setSuccess(true);
            return result;
        }
        list.add("该用户不存在或数据缺失");
        result.setData(list);
        mo.setText("更新失败！");
        result.setMsg(mo);
        result.setSuccess(false);
        return result;

    }


    @GetMapping("/api/excel")
    @ResponseBody
    public void  test6(HttpServletResponse response,String depart) throws IOException{

        //工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("测试表");
        List<User> queryAll = userDao.getUserListByDepart(depart);
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

    @GetMapping("/api/logout")
    @ResponseBody
    public Result logout() {
        Result result = new Result();
        Message_oa mo = new Message_oa();

        List<String> list  = new ArrayList<String>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() !=null)
        {
            //若用户不为空则注销
            subject.logout();
        }
        else {
            //若用户已为空则返回
            result.setSuccess(true);
            mo.setLogin(false);
            mo.setText("早已注销");
            result.setMsg(mo);
            result.setData(list);
            return result;
        }
        subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null){
            //注销成功
            mo.setLogin(false);
            mo.setText("注销成功");
            result.setSuccess(true);
            result.setMsg(mo);
            result.setData(list);
            return result;

        }else {
            //注销失败
            mo.setLogin(true);
            mo.setText("注销成功");
            result.setSuccess(false);
            result.setMsg(mo);
            result.setData(list);
            return result;
        }

    }

}
