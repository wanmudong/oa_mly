package com.oa.me.controller;

import com.oa.me.Dao.DictDao;
import com.oa.me.Entity.*;
import com.oa.me.Service.ReportService;
import com.oa.me.domain.*;
import com.oa.me.utils.Format;
import com.oa.me.utils.mapperUser;
import com.oa.me.utils.timeUtil;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.xssf.usermodel.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenjiehao on 2018/9/27
 */
@RequestMapping("/api/report")
@RestController
@EnableAutoConfiguration
public class ReportController {

    @Resource
    private ReportService reportService;

    @Resource
    private DictDao dictDao;

    /**
     * 获取个人汇报历史
     * @param uid1
     * @return
     */
    @GetMapping("/history")
    public Result history(String uid1) {
        Result result = new Result();
        JCondition jCondition = new JCondition();
        List list = new ArrayList();
        Message_oa mo = new Message_oa();
        int uid = 0;
        SysUser sysuser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (uid1 == null || uid1.equals("")) {
            uid = sysuser.getId();
        } else {
            uid = Integer.parseInt(uid1);
        }

        jCondition.setUid(uid);

        mo.setLogin(true);
//        if (sysuser == null) {
//            //用户已注销
//            result.setMsg(mo);
//            result.getMsg().setText("用户已注销");
//            return result;
//        }
        try {
            list = reportService.getHistory(uid);
        }catch (Exception e)
        {
            mo.setText("获取失败！");
            mo.setLogin(true);
            result.setMsg(mo);
            result.setSuccess(false);
            return result;
        }
      //  if (list != null) {

//            RRecruit rRecruit= mapperUser.mapperRRecruit(recruit,dictDao);
//            result.setData(list);
            result.setConditions(jCondition);
            mo.setText("获取成功！");
            mo.setLogin(true);
            result.setData(list);
            result.setMsg(mo);
            result.setSuccess(true);
            return result;
      //  }



    }

//    @GetMapping("/status")
//    public RResult getStatus(){
//        RResult rResult = new RResult();
//        Message_oa mo = new Message_oa();
//        SysUser sysuser = (SysUser) SecurityUtils.getSubject().getPrincipal();
//         int uid = Integer.parseInt(sysuser.getUsername());
//        if (sysuser == null) {
//            //用户已注销
//            rResult.setMsg(mo);
//            rResult.getMsg().setText("用户已注销");
//            return rResult;
//        }
//        RDate rDate = reportService.getStatus(uid);
//
//        if (rDate != null)
//        {
//
//            mo.setText("获取成功！");
//            mo.setLogin(true);
//            rResult.setData(rDate);
//            rResult.setMsg(mo);
//            rResult.setSuccess(true);
//            return rResult;
//        }
//
//        mo.setText("获取失败！");
//        mo.setLogin(true);
//        rResult.setMsg(mo);
//        rResult.setSuccess(false);
//        return rResult;
//
//    }

    /**
     * 获取当前汇报状态
     * @param uid
     * @return
     */
    @GetMapping("/status")
    public RResult getStatus(String uid) {
        RResult rResult = new RResult();
        Message_oa mo = new Message_oa();
        int uid1 = 0;
        SysUser sysuser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (uid == null || uid.equals("")) {
            uid1 = sysuser.getId();
        } else {
            uid1 = Integer.parseInt(uid);
        }
        mo.setLogin(true);
//        if (sysuser == null) {
//            //用户已注销
//            rResult.setMsg(mo);
//            rResult.getMsg().setText("用户已注销");
//            return rResult;
//        }
        RDate rDate = reportService.getStatus(uid1);

        if (rDate != null) {

            mo.setText("获取成功！");
            mo.setLogin(true);
            rResult.setData(rDate);
            rResult.setMsg(mo);
            rResult.setSuccess(true);
            return rResult;
        }

        mo.setText("获取失败！");
        mo.setLogin(true);
        rResult.setMsg(mo);
        rResult.setSuccess(false);
        return rResult;

    }

    /**
     * 在权限允许的条件下获取成员的汇报信息
     * @param campus
     * @param contact
     * @param depart
     * @return
     */
    @GetMapping("")
    public Result getReportsbByContact(String campus, String contact, String depart,String work_start_date) {

        Result result = new Result();
        Message_oa mo = new Message_oa();
        List list = new ArrayList();
        SysUser sysuser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        JCondition jCondition = new JCondition();
        jCondition.setCampus(Format.getFormat(campus));
        jCondition.setDepart(Format.getFormat(depart));
        jCondition.setPeriod("");
        jCondition.setContent(Format.getFormat(contact));
        result.setConditions(jCondition);

        depart = dictDao.getDepartIdByName(depart);
        campus = dictDao.getCampusIdByName(campus) == "0" ? "" : dictDao.getCampusIdByName(campus);

        mo.setLogin(true);
//        if (sysuser == null) {
//            //用户已注销
//            result.setMsg(mo);
//            result.getMsg().setText("用户已注销");
//            return result;
//        } else {
//            mo.setLogin(true);
//        }


        String depart_0 = sysuser.getDepart();
        Integer role = Integer.valueOf(sysuser.getRole());
        String campus_0 = sysuser.getCampus();

        try {
            if (role == 0) {
                result.setData(list);
                mo.setText("获取失败,权限不足！");
                result.setMsg(mo);
                result.setSuccess(true);
                return result;
            } else if (role == 1) {
                list = reportService.getReportByContent(contact, depart_0, campus_0,work_start_date);
            } else if (role == 2 || role == 3) {
                list = reportService.getReportByContentAndCampus(contact, depart, campus,work_start_date);
            }

        }catch (Exception e)
        {
            mo.setText("获取失败！");

            result.setMsg(mo);
            result.setSuccess(false);
            result.setData(list);
            return result;
        }
        //if (!list.isEmpty()) {


            result.setData(list);
//            result.setData(jData);
            mo.setText("获取成功！");
            result.setMsg(mo);
            result.setSuccess(true);
            return result;
   //     }


//        jResult.setData(jData);




    }

//

    /**
     * 修改汇报状态
     * @param rDate
     * @return
     */
    @PostMapping("/status")
    public RResult setStatus(RDate rDate) {
        RResult rResult = new RResult();
        Message_oa mo = new Message_oa();
        mo.setLogin(true);
//        SysUser sysuser = (SysUser) SecurityUtils.getSubject().getPrincipal();
//        if (sysuser == null) {
//            //用户已注销
//            rResult.setMsg(mo);
//            rResult.getMsg().setText("用户已注销");
//            return rResult;
//        }
        //TODO:权限控制
        boolean success=false;


            if (rDate.getReport_stat().equals("close")) {

                success = reportService.setStatusClose(rDate.getReport_stat());
            } else {

                success = reportService.setStatus(rDate);
            }




        if (success) {

            mo.setText("修改成功！");
            mo.setLogin(true);
//            rResult.setData(rDate);
            rResult.setMsg(mo);
            rResult.setSuccess(true);
            return rResult;
        }
        mo.setText("修改失败！");
        mo.setLogin(true);
        rResult.setMsg(mo);
        rResult.setSuccess(false);
        return rResult;


    }

    /**
     * 存储接收到的汇报信息
     * @param content
     * @param suggestion
     * @return
     */
    @PostMapping("")
    public RResult getReportsbByContact(String content, String suggestion)
    {
        RResult rResult = new RResult();
        Message_oa mo = new Message_oa();
        int uid1 = 0;
        SysUser sysuser = (SysUser) SecurityUtils.getSubject().getPrincipal();
//        if (uid == null || uid.equals("")) {
        uid1 = sysuser.getId();
//        } else {
//            uid1 = Integer.parseInt(uid);
//        }

        mo.setLogin(true);
//        if (sysuser == null) {
//            //用户已注销
//            rResult.setMsg(mo);
//            rResult.getMsg().setText("用户已注销");
//            return rResult;
//        }
        boolean success;

//        try {
             success = reportService.setReport(uid1, content, suggestion);
//        }catch (Exception e){

//        }


        if (success){

            RDate rDate = reportService.getStatus(uid1);
            mo.setText("汇报成功！");
            mo.setLogin(true);
            rResult.setData(rDate);
            rResult.setMsg(mo);
            rResult.setSuccess(true);
            return rResult;
       }
        mo.setText("汇报失败！");
        mo.setLogin(true);
        rResult.setMsg(mo);
        rResult.setSuccess(false);
        return rResult;



    }

    /**
     * 对某一汇报进行审核
     * @param id
     * @param comment
     * @param rate
     * @param salary_sug
     * @param salary
     * @return
     */
    @PostMapping("/review")
    public Result review(int id,String comment,String rate,String salary_sug,String salary){
        Result result = new Result();
        Message_oa mo = new Message_oa();
        mo.setLogin(true);
        int uid1 = 0;
//        SysUser sysuser = (SysUser) SecurityUtils.getSubject().getPrincipal();
//        if (uid == null || uid.equals("")) {
//        uid1 = sysuser.getId();
//        } else {
//            uid1 = Integer.parseInt(uid);
//        }
//        if (sysuser == null) {
//            //用户已注销
//            result.setMsg(mo);
//            result.getMsg().setText("用户已注销");
//            return result;
//        }
        boolean success;
//        try {
             success = reportService.setReportByAdmin(id, comment, rate, salary_sug, salary);
//        }catch (Exception e){

//        }


        if (success){

//            RDate rDate = reportService.getStatus(uid1);
            mo.setText("审核成功！");
            mo.setLogin(true);
            result.setMsg(mo);
            result.setSuccess(true);
            return result;
        }
        mo.setText("审核失败！");
        mo.setLogin(true);
        result.setMsg(mo);
        result.setSuccess(false);
        return result;


    }

    /**
     * 在权限允许的条件下获取条件选择后的excel文件
     * @param response
     * @param end_date
     * @param start_date
     * @throws IOException
     */
    @GetMapping("/excel")
    @ResponseBody
    public void test6(HttpServletResponse response, String end_date,String start_date) throws IOException {
        List<RReport> list = new ArrayList<RReport>();

        SysUser sysuser = (SysUser) SecurityUtils.getSubject().getPrincipal();

        String depart1 = sysuser.getDepart();
        Integer role = Integer.valueOf(sysuser.getRole());
        String campus_0 = sysuser.getCampus();

        if (role == 0) {

        } else if (role == 1) {
            //只能获取本部门的
          list =  reportService.getReportExcel(depart1,campus_0,start_date,end_date);
        } else if (role == 2 || role == 3) {
            list =  reportService.getReportAllExcel(start_date,end_date);
        }
        if (list!=null) {
            //工作簿
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("测试表");
            //设置导出的表的名字
            String fileName = "memberInfo.xls";
            //设置头

            String[] headers = {"部门", "姓名", "学号", "性别", "学院", "手机号码", "银行卡号", "工资", "汇报", "汇报时间"};
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
            for (RReport rReport : list) {

                row1 = sheet.createRow(rowNum);
                System.out.println(rowNum);
                row1.createCell(0).setCellValue(dictDao.getDepartNameById(Integer.parseInt(rReport.getDepart())));
                row1.createCell(1).setCellValue(rReport.getUsername());
                row1.createCell(2).setCellValue(String.valueOf(rReport.getStuid()));
                row1.createCell(3).setCellValue(rReport.getSex());
                row1.createCell(4).setCellValue(rReport.getCollege());
                row1.createCell(5).setCellValue(rReport.getPhone());
                row1.createCell(6).setCellValue(rReport.getDebitcard());
                row1.createCell(7).setCellValue(rReport.getSalary0());
                row1.createCell(8).setCellValue(rReport.getContent());
                row1.createCell(9).setCellValue(timeUtil.dateTime(Long.parseLong(rReport.getTime_report()),"yyyy-MM-dd"));


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
