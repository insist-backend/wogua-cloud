package center.helloworld.server.blog.service.app.web;

import center.helloworld.common.core.base.Result;
import center.helloworld.common.entity.blog.BlogUpload;
import center.helloworld.server.blog.service.app.properties.BlogProperties;
import center.helloworld.server.blog.service.app.service.BlogUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/5/9 15:50
 */
@RestController
@RequestMapping("blogUpload")
public class BlogUploadController {

    @Autowired
    private BlogProperties blogProperties;

    @Autowired
    private BlogUploadService blogUploadService;

    /**
     * 上传图片
     * @param file
     * @return
     */
    @PostMapping("upload")
    public Result upload(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }
        BlogUpload blogUpload = new BlogUpload();
        String extendName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String replace = UUID.randomUUID().toString().replace("-", "");
        // 创建新的图片名称防止名称冲突
        String newFileName = replace + extendName;
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(blogProperties.getImgPath() , newFileName);
            // 上传图片
            Files.write(path, bytes);
            // 保存到数据库
            blogUpload.setUuid(replace);
            blogUpload.setName(file.getOriginalFilename());
            blogUpload.setUrl(blogProperties.getImgPath() + "/" + newFileName);
            blogUploadService.save(blogUpload);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.ok(blogProperties.getImgPath() + "/" + newFileName);
    }

    /**
     * 根据ID删除上传图片
     * @return
     */
    @DeleteMapping("delBlogUpload/{blogUploadId}")
    public Result delBlogUpload(@PathVariable("blogUploadId") String id) throws IOException {
        BlogUpload blogUpload = blogUploadService.getById(id);
        if(blogUpload != null) {
            // 删除图片
            Path path = Paths.get(blogUpload.getUrl());
            Files.delete(path);
            blogUploadService.removeById(id);
        }
        return Result.ok();
    }
}
