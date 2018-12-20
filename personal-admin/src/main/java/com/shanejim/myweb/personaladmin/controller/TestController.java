package com.shanejim.myweb.personaladmin.controller;

import com.shanejim.myweb.personalmodel.entity.PayMall;
import com.shanejim.myweb.personalmodel.enums.CodeEnums;
import com.shanejim.myweb.personalmodel.query.TestQuery;
import com.shanejim.myweb.personalmodel.response.Result;
import com.shanejim.myweb.personalmodel.response.UploadReturn;
import com.shanejim.myweb.personalmodel.utils.FileUtil;
import com.shanejim.myweb.personalmodel.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @GetMapping("/UserExcelDownloads")
    public void downloadAllClassmate(HttpServletResponse response) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<PayMall> classmateList = new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook();
        String fileName = "userinf" + ".xlsx";//设置要导出的文件的名字


        XSSFSheet sheet = workbook.createSheet("信息表");
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        for (int i = 0; i < 70000; i++) {
            PayMall chatRoom = new PayMall();
            chatRoom.setAliAppid("名称123");
            chatRoom.setAliAppPrivateKey("名称333");
            chatRoom.setAliAppPublicKey("名称555");
            chatRoom.setHostUrl("关键词");
            classmateList.add(chatRoom);
        }

        String[] headers = {"名称", "用户个数", "到期时间", "关键词"};
        //headers表示excel表中第一行的表头

        XSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (PayMall teacher : classmateList) {
            XSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(teacher.getAliAppid());
            row1.createCell(1).setCellValue(teacher.getAliAppPrivateKey());
            row1.createCell(2).setCellValue(teacher.getAliAppPrivateKey());
            row1.createCell(3).setCellValue(teacher.getAliPublicKey());
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
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
