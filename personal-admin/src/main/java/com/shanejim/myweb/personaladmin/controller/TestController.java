package com.shanejim.myweb.personaladmin.controller;

import com.shanejim.myweb.personalmodel.enums.CodeEnums;
import com.shanejim.myweb.personalmodel.query.TestQuery;
import com.shanejim.myweb.personalmodel.response.Result;
import com.shanejim.myweb.personalmodel.response.UploadReturn;
import com.shanejim.myweb.personalmodel.utils.FileUtil;
import com.shanejim.myweb.personalmodel.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

/**
 * @author shanejim
 * @description todo
 * @date 2018/10/21
 */
@RestController
public class TestController {
    @Autowired
    JedisPool jedisPool;

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    //处理文件上传
    @PostMapping("/testuploadimg")
    public Result uploadImg(@RequestParam("file") MultipartFile file) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();

        try {
            FileUtil.uploadFile(file.getBytes(), uploadFolder, fileName);
        } catch (Exception e) {
            //logger.error("上传文件失败：" + e.getMessage());
            ResultUtil.error(CodeEnums.COMMON_ERR.getCode(), "上传失败");
        }
        UploadReturn uploadReturn = new UploadReturn();
        uploadReturn.setPath(staticAccessPath.replace("**", "") + fileName);
        //返回json
        return ResultUtil.success(uploadReturn);
    }

    @PostMapping("/testgetauthinfo")
    public Result testgetauthinfo() {
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
//                .getAuthentication()
//                .getPrincipal();
        //GrantedAuthority[] authorities = userDetails.getAuthorities();

        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();

        return ResultUtil.success(auth);
    }

    @PostMapping("/testjedis")
    public Result testjedis() {
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
//                .getAuthentication()
//                .getPrincipal();
        //GrantedAuthority[] authorities = userDetails.getAuthorities();
        jedisPool.getResource().set("mykey", "这是我的key");

        return ResultUtil.success();
    }

    @PreAuthorize("hasAuthority('admintest22')")
    @ApiOperation(value = "测试权限", notes = "测试权限")
    @PostMapping("/testPermission")
    public Result testPermission() {

        return ResultUtil.success();
    }

    @PreAuthorize("hasAuthority('admintest22') or hasAuthority('admintest')")
    @ApiOperation(value = "测试或权限", notes = "测试或权限")
    @PostMapping("/testOrPermission")
    public Result testOrPermission() {

        return ResultUtil.success();
    }

    @PreAuthorize("hasAuthority('admintest22') and hasAuthority('admintest')")
    @ApiOperation(value = "测试且权限", notes = "测试且权限")
    @PostMapping("/testAndPermission")
    public Result testAndPermission() {

        return ResultUtil.success();
    }

    @PostMapping("/testTime")
    public Result testTime(@RequestBody TestQuery query) {

        return ResultUtil.success();
    }


    //分布式锁
      /*  Jedis jedis = RedisUtil.getConn();
        //jedis.set("key", "哈哈哈");
        String uuid = UUID.randomUUID().toString();

        boolean result = RedisLockUtil.tryGetDistributedLock(jedis, "fenbushikey", uuid, 100000);

        RedisLockUtil.releaseDistributedLock(jedis, "fenbushikey", uuid);

        if (jedis != null) {
            jedis.close();
        }*/


}
