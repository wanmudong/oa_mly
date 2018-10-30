package com.oa.me.modules.news.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa.me.modules.common.annotation.SysLog;
import com.oa.me.modules.common.utils.MyPageInfo;
import com.oa.me.modules.common.utils.Result;
import com.oa.me.modules.news.entity.Article;
import com.oa.me.modules.news.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


/**
 * 文章管理
 *
 * @author chenliangliang
 * @date 2018-02-21 15:42:00
 */
@Slf4j
@RestController
@EnableAutoConfiguration
@RequestMapping("api/article")
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    protected ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 列表
     */
  //  @RequiresPermissions("news:article:list")
    @GetMapping("/list")
    public Result list(String tags) {

        MyPageInfo page = articleService.queryPage(tags);

        return Result.OK().put("data",page);
    }



    @GetMapping("/info/{id}")
  //  @RequiresPermissions("news:article:info")
    public Result detail(@PathVariable("id") Long id){
        Article article=articleService.getDetail(id);
        return Result.OK().put("data",article);
    }


//    /**
//     * 审核文章
//     */
//  //  @SysLog("审核文章")
//    @GetMapping("/audit/{id}")
////    @RequiresPermissions("news:article:audit")
//    public Result audit(@PathVariable("id") Long id,String comment){
//
//
//        return Result.error("您无审核权限");
//    }

//
//    /**
//     * 信息
//     */
////    @GetMapping("/info/{id}")
////    @RequiresPermissions("news:article:info")
////    public Result info(@PathVariable("id") Long id) {
////            Article article = articleService.selectById(id);
////
////        return Result.OK().put("article", article);
////    }
//

    /**
     * 更新管理后台文章
     */
    @GetMapping("/upload")
//    @RequiresPermissions("news:article:upload")
    public Result upload(){

        String msg = articleService.uploadArticle();
        if ("success".equals(msg)){
            return Result.OK();
        }
        return Result.error(msg);
    }

    /**
     * 审核文章
     */
    @PostMapping("/audit/{id}")
    //@RequiresPermissions("news:article:audit")
    public Result audit(@PathVariable("id") Long id,  String comment,  Long pass) {
        Integer num;
        try {
          num = articleService.updateAudit(id,pass,comment);
        }catch (Exception e)
        {
            log.info("审核({})失败",id);
            log.info("错误如下",e);
            return Result.error("审核失败");
        }
        return num==1?Result.OK():Result.error("审核信息有误");
    }


    /**
     * 保存（json格式）
     */
    @PostMapping("/save")
//  @RequiresPermissions("news:article:save")
    public Result save( @RequestBody String json) {
        log.info("收到了({})",json);
        try {
            articleService.insertArticle(json);
        }catch (Exception e){
            log.info("提交数据有误",e);
            return Result.error("提交数据有误");
        }
        return Result.OK();
    }

    /**
     * 修改
     */
    @SysLog("修改文章")
    @PostMapping("/update")
  //  @RequiresPermissions("news:article:update")
    public Result update(@RequestBody Article article) {
            articleService.updateById(article);
        return Result.OK();
    }


    /**
     * 历史文章
     */
    @SysLog("历史文章")
    @GetMapping("/history")
   // @RequiresPermissions("news:article:history")
    public Result history( String username,@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        MyPageInfo myPageInfo = articleService.getHistory(username);
        return Result.OK().put("data",myPageInfo);
    }

//
//    /**
//     * 删除
//     */
//    @SysLog("删除文章")
//    @PostMapping("/delete")
//    @RequiresPermissions("news:article:delete")
//    public Result delete(@RequestBody Long[]ids) {
//            articleService.deleteBatchIds(Arrays.asList(ids));
//
//        return Result.OK();
//    }

}
