package center.helloworld.server.blog.service.app.web;

import center.helloworld.common.core.base.Result;
import center.helloworld.common.entity.blog.BlogTag;
import center.helloworld.server.blog.service.app.service.BlogArticleTagService;
import center.helloworld.server.blog.service.app.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/5/9 15:50
 */
@RestController
@RequestMapping("blogTag")
public class BlogTagController {

    @Autowired
    private BlogTagService tagService;

    @Autowired
    private BlogArticleTagService articleTagService;

    /**
     * 列表
     * @param searchKey
     * @return
     */
    @GetMapping("list")
    public Result list(String searchKey) {
        List<BlogTag> tagList = tagService.list(searchKey);
        return Result.ok(tagList);
    }

    /**
     * 添加
     * @param blogTag
     * @return
     */
    @PostMapping
    public Result add(@RequestBody BlogTag blogTag) {
        tagService.save(blogTag);
        return Result.ok();
    }

    /**
     * 修改
     * @param blogTag
     * @return
     */
    @PutMapping("{id}")
    public Result update(@RequestBody BlogTag blogTag,@PathVariable("id") Integer id) {
        blogTag.setId(id);
        tagService.updateById(blogTag);
        return Result.ok();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @Transactional
    public Result delete(@PathVariable("id") Integer id) {
        // 删除类别
        tagService.removeById(id);
        // 删除文章标签关系
        articleTagService.deleteByTagId(id);
        return Result.ok();
    }
}
