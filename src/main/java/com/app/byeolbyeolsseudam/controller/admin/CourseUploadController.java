package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.domain.course.CourseDTO;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseUploadController {

//    @PostMapping("/upload")
//    public List<CourseDTO> uploadProfile(List<MultipartFile> upload) throws IOException {
//        String rootPath = "C:/upload/course";
//        String uploadFileName = null;
//        List<CourseDTO> files = new ArrayList<>();
//
//        File uploadPath = new File(rootPath, createDirectoryByNow());
//        if(!uploadPath.exists()){
//            uploadPath.mkdirs();
//        }
//
//        for (MultipartFile multipartFile : upload){
//            CourseDTO courseDTO = new CourseDTO();
//            UUID uuid = UUID.randomUUID();
//            String fileName = multipartFile.getOriginalFilename();
//            uploadFileName = uuid.toString() + "_" + fileName;
//            courseDTO.setCourseFileName(fileName);
//            courseDTO.setCourseFileUuid(uuid.toString());
//            courseDTO.setCourseFilePath("/upload/course/" + createDirectoryByNow());
//
//            File saveFile =new File(uploadPath, uploadFileName);
//            multipartFile.transferTo(saveFile);
//
//            files.add(courseDTO);
//
//        }
//        return files;
//    }

//    public String createDirectoryByNow(){
//        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
//        Date now = new Date();
//        return format.format(now);
//    }
}
