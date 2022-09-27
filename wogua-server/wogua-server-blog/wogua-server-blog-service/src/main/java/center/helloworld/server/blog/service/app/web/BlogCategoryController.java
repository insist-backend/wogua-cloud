package center.helloworld.server.blog.service.app.web;

import center.helloworld.common.core.base.Result;
import center.helloworld.common.entity.blog.BlogCategory;
import center.helloworld.server.blog.service.app.service.BlogArticleCategoryService;
import center.helloworld.server.blog.service.app.service.BlogCategoryService;
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
@RequestMapping("blogCategory")
public class BlogCategoryController {

    @Autowired
    private BlogCategoryService categoryService;

    @Autowired
    private BlogArticleCategoryService articleCategoryService;

    /**
     * 列表
     * @param searchKey
     * @return
     */
    @GetMapping("list")
    public Result list(String searchKey) {
        List<BlogCategory> categoryList = categoryService.list(searchKey);
        return Result.ok(categoryList);
    }

    /**
     * 添加
     * @param blogCategory
     * @return
     */
    @PostMapping
    public Result add(@RequestBody BlogCategory blogCategory) {
        categoryService.save(blogCategory);
        return Result.ok();
    }

    /**
     * 修改
     * @param blogCategory
     * @return
     */
    @PutMapping("{id}")
    public Result update(@RequestBody BlogCategory blogCategory,@PathVariable("id") Integer id) {
        blogCategory.setId(id);
        categoryService.updateById(blogCategory);
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
        categoryService.removeById(id);
        // 删除文章类别关系
        articleCategoryService.deleteByCategoryId(id);
        return Result.ok();
    }
}
