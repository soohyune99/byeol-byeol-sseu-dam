package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.domain.banner.BannerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/file/*")
@RequiredArgsConstructor
@Slf4j
public class BannerUploadController {


    @PostMapping("/upload")
    public List<BannerDTO> upload(List<MultipartFile> upload) throws IOException {
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
            bannerDTO.setBannerPath("/upload/banner/" + createDirectoryByNow());

            File saveFile =new File(uploadPath, uploadFileName);
            multipartFile.transferTo(saveFile);

            files.add(bannerDTO);

        }
        return files;
    }
    @PostMapping("/delete")
    public void delete(String uploadPath,String bannerUuid, String bannerName){
        File file = new File("C:/", uploadPath+"/"+bannerUuid+"_"+bannerName);

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