package com.oa.me.controller;

import com.oa.me.Dao.DictDao;
import com.oa.me.Entity.*;
import com.oa.me.Service.RecruitService;
import com.oa.me.domain.*;
import com.oa.me.utils.Format;
import com.oa.me.utils.LoginUtil;
import com.oa.me.utils.mapperUser;
import com.oa.me.utils.timeUtil;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.xssf.usermodel.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjiehao on 2018/9/22
 */
@RestController
@EnableAutoConfiguration
public class RecruitController {
    @Resource
    private RecruitService recruitService;

    @Resource
    private DictDao dictDao;


    /**
     * 获取当前所有的招新信息
     * @param content
     * @param depart
     * @param campus
     * @return
     */
    @GetMapping("/api/recruit")
    @RequiresPermissions("user:recruit:list")
    @ResponseBody
    public JResult getRecruit(String content,String depart,String campus){
        Result result = new Result();
        List<Recruit> list= new ArrayList<Recruit>();
        Message_oa mo = new Message_oa();
        SysUser sysuser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        List<JUser> jUserList = new ArrayList<JUser>();
        JData jData = new JData();
        JResult jResult = new JResult();

        JCondition jCondition = new JCondition();
        jCondition.setCampus(Format.getFormat(campus));
        jCondition.setDepart(Format.getFormat(depart));
        jCondition.setPeriod("");
        jCondition.setContent(Format.getFormat(content));
        jData.setConditions(jCondition);

        depart = dictDao.getDepartIdByName(depart);
        campus = dictDao.getCampusIdByName(campus)=="0"?"":dictDao.getCampusIdByName(campus);

        mo.setLogin(true);
//        if (sysuser == null)
//        {
//            //用户已注销
//            jResult.setMsg(mo);
//            jResult.getMsg().setText("用户已注销");
//            return jResult;
//        }else {
//            mo.setLogin(true);
//        }


        String depart_0 = sysuser.getDepart();
        Integer role =Integer.valueOf( sysuser.getRole());
        String campus_0 = sysuser.getCampus();

        try {
            /**
             * 根据权限进行不同的招新查询
             */
            //TODO:利用动态sql减少系统的逻辑处理
            if (role == 0) {
                jData.setData(list);
                mo.setText("获取失败,权限不足！");
                jResult.setMsg(mo);
                jResult.setSuccess(false);
                return jResult;
            } else if (role == 1) {
                list = recruitService.getRecruitByContent(content, depart_0, campus_0);
            } else if (role == 2 || role == 3) {

                list = recruitService.getRecruitByContentAndCampus(content, depart, campus);
            }

        }catch (Exception e){
            jData.setData(jUserList);
            jResult.setData(jData);

            mo.setText("获取失败！");

            jResult.setMsg(mo);
            jResult.setSuccess(false);
            return jResult;
        }
      //  if (!list.isEmpty())
     //   {

            for (Recruit recruit : list) {
                JUser jUser = mapperUser.mapperJUser(recruit, dictDao);
                jUserList.add(jUser);
            }

            jData.setData(jUserList);
            jResult.setData(jData);
            mo.setText("获取成功！");
            jResult.setMsg(mo);
            jResult.setSuccess(true);
            return jResult;
   //     }


    }


    /**
     * 修改某条招新信息
     * @param id
     * @param status
     * @param desc
     * @param depart
     * @return
     */
    @PostMapping ("api/recruit/{id}")
    @RequiresPermissions("user:recruit:update")
    public RResult update(@PathVariable("id") int id, int status, String desc, String depart){
    //    RRecruit rRecruit = new RRecruit();
        RResult rResult = new RResult();
//        List list = new ArrayList();
        Message_oa mo = new Message_oa();
        mo.setLogin(true);
        //Result result = new Result();
    //    Message_oa mo = new Message_oa();
      //  mo.setLogin(LoginUtil.isLogin());
  //      List<Recruit> list =  new ArrayList<Recruit>();
        SysUser sysuser = (SysUser) SecurityUtils.getSubject().getPrincipal();
//        if (sysuser == null) {
//            //用户已注销
//            rResult.setMsg(mo);
//            rResult.getMsg().setText("用户已注销");
//            return rResult;
//        }
        Recruit recruit = new Recruit();

            recruit = recruitService.update(id, status, desc, depart);


        if (recruit != null)
        {

            RRecruit rRecruit= mapperUser.mapperRRecruit(recruit,dictDao);
//            result.setData(list);

            mo.setText("更新成功！");
            rResult.setData(rRecruit);
            rResult.setMsg(mo);
            rResult.setSuccess(true);
            return rResult;
        }
        mo.setText("更新失败！");
        rResult.setMsg(mo);
        rResult.setSuccess(false);
        return rResult;



    }

//    /**
//     * 对一条招新信息的插入
//     * @param Recruit
//     * @return
//     */
//    @PostMapping ("api/recruit/apply")
//    public Result apply(Recruit recruit){
//
//
//        Result result = new Result();
//        Message_oa mo = new Message_oa();
//        mo.setLogin(LoginUtil.isLogin());
//        List list =  new ArrayList();
//
//        boolean success = recruitService.apply(recruit);
//        if (success)
//        {
//
//            result.setData(list);
//
//            mo.setText("申请成功！");
//            result.setMsg(mo);
//            result.setSuccess(true);
//            return result;
//        }
//        result.setData(list);
//        mo.setText("申请失败！");
//        result.setMsg(mo);
//        result.setSuccess(false);
//        return result;
//
//    }

    /**
     * 单独获取一条招新信息
     * @param id
     * @return
     */
    @GetMapping ("api/recruit/{id}")
    @RequiresPermissions("user:recruit:info")
    public RResult getRecruitById(@PathVariable("id") int id)
    {

        RResult rResult = new RResult();
//        List list = new ArrayList();
        Message_oa mo = new Message_oa();

        mo.setLogin(true);
//        SysUser sysuser = (SysUser) SecurityUtils.getSubject().getPrincipal();
//        if (sysuser == null) {
//            //用户已注销
//            rResult.setMsg(mo);
//            rResult.getMsg().setText("用户已注销");
//            return rResult;
//        }
        Recruit recruit = new Recruit();

            recruit  = recruitService.getRecruitById(id);



        if (recruit != null) {

            RRecruit rRecruit= mapperUser.mapperRRecruit(recruit,dictDao);
            mo.setText("获取成功！");

            rResult.setMsg(mo);
            rResult.setSuccess(true);
            rResult.setData(rRecruit);
            return rResult;
        }
        mo.setText("获取失败！");
        rResult.setMsg(mo);
        rResult.setSuccess(false);

        return rResult;



    }

    /**
     *将根据相关查询条件获取
     * @param response
     * @param depart
     * @param campus
     * @param content
     * @param period
     * @throws IOException
     */
    @GetMapping("/api/recruit/excel")
    @ResponseBody
    @RequiresPermissions("user:recruit:list")
    public void gerExcel(HttpServletResponse response, String depart, String  campus, String content, String period) throws IOException {
        List<Recruit> list = new ArrayList<Recruit>();
        List<RRecruit> list1 = new ArrayList<RRecruit>();
        Message_oa mo = new Message_oa();
        mo.setLogin(true);
        depart = dictDao.getDepartIdByName(depart);
        campus = dictDao.getCampusIdByName(campus)=="0"?"":dictDao.getCampusIdByName(campus);



        SysUser sysuser = (SysUser) SecurityUtils.getSubject().getPrincipal();

        String depart1 = sysuser.getDepart();
        Integer role = Integer.valueOf(sysuser.getRole());
        String campus_0 = sysuser.getCampus();

        if (role == 0) {

        } else if (role == 1) {
            //只能获取本部门的

            list = recruitService.getRecruitByContent(content, depart1, campus_0);


        } else if (role == 2 || role == 3) {

            list = recruitService.getRecruitByContentAndCampus(content, depart, campus);
        }

        for (Recruit recruit:list)
        {
           RRecruit rRecruit = mapperUser.mapperRRecruit(recruit,dictDao);
           list1.add(rRecruit);
        }

        if (list1!=null) {
            //工作簿
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("测试表");

//            List<User> queryAll = userDao.getUserListByDepart("3");
//            设置导出的表的名字
            String fileName = "memberInfo.xls";
            //设置头

            String[] headers = {"部门", "姓名", "学号", "性别", "学院", "专业", "qq号", "手机号码", "申请时间", "自我介绍"};
            XSSFRow row = sheet.createRow(0);
            //设置为居中加粗
            XSSFCellStyle style = workbook.createCellStyle();
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style.setFont(font);

            for (int i = 0; i < headers.length; i++) {
                XSSFCell cell = row.createCell(i);
                XSSFRichTextString text = new XSSFRichTextString(headers[i]);
                cell.setCellValue(text);
                cell.setCellStyle(style);

            }

            int rowNum = 1;
            XSSFRow row1;
            for (Recruit recruit : list) {
                row1 = sheet.createRow(rowNum);
                row1.createCell(0).setCellValue(dictDao.getDepartNameById(recruit.getDepart()));
                row1.createCell(1).setCellValue(recruit.getName());
                row1.createCell(2).setCellValue(String.valueOf(recruit.getStuid()));
                row1.createCell(3).setCellValue(recruit.getSex());
                row1.createCell(4).setCellValue(recruit.getCollege());
                row1.createCell(5).setCellValue(recruit.getMajor());
                row1.createCell(6).setCellValue(recruit.getQq());
                row1.createCell(7).setCellValue(recruit.getPhone());
                row1.createCell(8).setCellValue(timeUtil.dateTime(Long.parseLong(recruit.getTime()),"yyyy-MM-dd"));
                row1.createCell(9).setCellValue(recruit.getSay());

                rowNum++;
            }

            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
            response.flushBuffer();
            workbook.write(response.getOutputStream());
            workbook.close();
        }
    }


}
