package com.oa.me.modules.news.controller;





import com.oa.me.modules.common.annotation.SysLog;
import com.oa.me.modules.common.utils.PageInfo;
import com.oa.me.modules.common.utils.Result;
import com.oa.me.modules.news.entity.Article;
import com.oa.me.modules.news.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
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
    @RequiresPermissions("news:article:list")
    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params) {
        PageInfo page = articleService.queryPage(params);
        return Result.OK().put("data",page);
    }


    @GetMapping("/info/{id}")
    @RequiresPermissions("news:article:info")
    public Result detail(@PathVariable("id") Long id){
        Article article=articleService.getDetail(id);
        return Result.OK().put("data",article);
    }


//    /**
//     * 审核文章
//     */
//    @SysLog("审核文章")
//    @GetMapping("/audit/{id}")
//    @RequiresPermissions("news:article:audit")
//    public Result audit(@PathVariable("id") Long id){
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
    @RequiresPermissions("news:article:audit")
    public Result audit(@PathVariable("id") Long id,  String comment,  Long state) {
        Integer num;
        try {
          num = articleService.updateAudit(id,state,comment);
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
            log.info("标题已存在",e);
            return Result.error("标题已存在");
        }
        return Result.OK();
    }

    /**
     * 修改
     */
    @SysLog("修改文章")
    @PostMapping("/update")
    @RequiresPermissions("news:article:update")
    public Result update(@RequestBody Article article) {
            articleService.updateById(article);
        return Result.OK();
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
