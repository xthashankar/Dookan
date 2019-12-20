/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist.bit.dookan.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Basanta Thapa
 */
public class FileUploadUtil {

    /**
     * Directory where uploaded files will be saved, its relative to the web
     * application directory.
     */
    static final String UPLOAD_DIR = "uploads";

    public static FileUploadDTO fileUpload(HttpServletRequest request, 
            HttpServletResponse response, String paramName)
            throws ServletException, IOException {

        FileUploadDTO fileUploadDto = new FileUploadDTO();
        fileUploadDto.setIsWrittenOnDisk(Boolean.FALSE);
        
        System.out.println(request.getHeader("Content-Disposition"));
        
        // Create path components to save the file
        String applicationPath = request.getServletContext().getRealPath("");
//        System.out.println("applicationPath = "+applicationPath);
//        String paths[] = applicationPath.split(Pattern.quote(File.separator));
//        StringBuilder uploadPath = new StringBuilder();
//        for(int i=0; i<paths.length-2;i++){
//            uploadPath.append(paths[i]).append(File.separator);
//            System.out.println(uploadPath);
//        }
//        System.out.println("Final upload path = "+uploadPath);
        // constructs path of the directory to save uploaded file
        String uploadFilePath = applicationPath + UPLOAD_DIR;
        
        // creates the save directory if it does not exists
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        final Part filePart = request.getPart(paramName);
        final String fileName = getFileName(filePart);

        OutputStream out = null;
        InputStream filecontent = null;
        try {
            out = new FileOutputStream(new File(uploadFilePath + File.separator + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            fileUploadDto.setIsWrittenOnDisk(Boolean.TRUE);
            fileUploadDto.setFileLocation(UPLOAD_DIR + File.separator + fileName);
        } catch (FileNotFoundException fne) {
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
        }
        return fileUploadDto;
    }

    private static String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
