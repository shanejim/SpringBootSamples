package com.shanejim.myweb.personalmodel.utils;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-26 16:12
 **/
public class FileUtil {
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
