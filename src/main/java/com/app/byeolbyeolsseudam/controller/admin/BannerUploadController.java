package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.domain.banner.BannerDTO;
import com.app.byeolbyeolsseudam.service.main.BannerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file/*")
@RequiredArgsConstructor
@Slf4j
public class BannerUploadController {


    @PostMapping("/upload")
    public List<BannerDTO> upload(List<MultipartFile> upload) throws IOException {
//        String rootPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\upload\\banner";
        String rootPath = "C:/upload/banner";
        String uploadFileName = null;
        List<BannerDTO> files = new ArrayList<>();

        File uploadPath = new File(rootPath, createDirectoryByNow());
        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }

        for (MultipartFile multipartFile : upload){
            BannerDTO bannerDTO = new BannerDTO();
            UUID uuid = UUID.randomUUID();
            String fileName = multipartFile.getOriginalFilename();
            uploadFileName = uuid.toString() + "_" + fileName;
            bannerDTO.setBannerName(fileName);
            bannerDTO.setBannerUuid(uuid.toString());
            bannerDTO.setBannerPath("/upload/banner/" + createDirectoryByNow() + "/" + uploadFileName);

            File saveFile =new File(uploadPath, uploadFileName);
            multipartFile.transferTo(saveFile);

            files.add(bannerDTO);

        }
        return files;
    }
    @PostMapping("/delete")
    public void delete(String uploadPath){
        String rootPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static";
        File file = new File(rootPath, uploadPath);

        if(file.exists()){
            file.delete();
        }
    }
    public String createDirectoryByNow(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date now = new Date();
        return format.format(now);
    }
}
