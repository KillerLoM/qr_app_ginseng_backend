package qr.app.backend.service;

import jakarta.activation.MimetypesFileTypeMap;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import qr.app.backend.model.Ginseng;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class FileUploadService {
    private static final List<String> ALLOWED_IMAGE_TYPES = Arrays.asList("image/jpeg", "image/png", "image/gif");

    public String uploadImageGinseng(MultipartFile file) throws IOException, Exception {
        String uploadDir = "D:\\GinSeng\\frontend\\qr_app\\src\\assets\\image\\GinSeng";
        try {
            String filePath = uploadDir + File.separator + file.getOriginalFilename();
            String filePath1 = "/assets/image/GinSeng/" + file.getOriginalFilename();
            String contentType = new MimetypesFileTypeMap().getContentType(file.getOriginalFilename());
            if (!ALLOWED_IMAGE_TYPES.contains(contentType)) {
                throw new Exception("The file is not supported. Support for png, jpeg, img and gif");
            }
            file.transferTo(new File(filePath));
            return filePath1;
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
    }
    public String uploadImageWine(MultipartFile file) throws IOException, Exception {
        String uploadDir = "D:\\GinSeng\\frontend\\qr_app\\src\\assets\\image\\WINE";
        try {
            String filePath = uploadDir + File.separator + file.getOriginalFilename();
            String filePath1 = "/assets/image/WINE/" + file.getOriginalFilename();
            String contentType = new MimetypesFileTypeMap().getContentType(file.getOriginalFilename());
            if (!ALLOWED_IMAGE_TYPES.contains(contentType)) {
                throw new Exception("The file is not supported. Support for png, jpeg, img and gif");
            }
            file.transferTo(new File(filePath));
            return filePath1;
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
    }
    public void handleUpload(Ginseng ginseng, String[] images, String certi){

    }
    public String uploadCertificate(MultipartFile file) throws IOException, Exception {
        String uploadDir = "D:\\GinSeng\\frontend\\qr_app\\src\\assets\\certificate\\ginseng";
        try {
            String filePath = uploadDir + File.separator + file.getOriginalFilename();
            String filePath1 = "/assets/certificate/ginseng/" + file.getOriginalFilename();
            String contentType = file.getContentType();
            String ALLOWED_PDF_TYPES = "application/pdf";
            assert contentType != null;
            if (!ALLOWED_PDF_TYPES.contains(contentType)) {
                throw new Exception("The file is not supported. Support for pdf");
            }
            file.transferTo(new File(filePath));
            return filePath1;
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
    }
    public String uploadAvatar(MultipartFile file) throws IOException, Exception {
        String uploadDir = "D:\\GinSeng\\frontend\\qr_app\\src\\assets\\image\\avatar";
        try {
            String filePath = uploadDir + File.separator + file.getOriginalFilename();
            String filePath1 = "/assets/image/avatar/" + file.getOriginalFilename();
            String contentType = new MimetypesFileTypeMap().getContentType(file.getOriginalFilename());
            if (!ALLOWED_IMAGE_TYPES.contains(contentType)) {
                throw new Exception("The file is not supported. Support for png, jpeg, img and gif");
            }
            file.transferTo(new File(filePath));
            return filePath1;
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
    }
}