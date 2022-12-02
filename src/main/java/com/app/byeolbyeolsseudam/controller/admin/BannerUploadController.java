package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.domain.banner.BannerDTO;
import com.app.byeolbyeolsseudam.service.main.BannerService;
import lombok.RequiredArgsConstructor;
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
public class BannerUploadController {
    private final BannerService bannerService;

    private final ServletContext servletContext;


    @PostMapping("/upload")
    public List<BannerDTO> upload(List<MultipartFile> upload) throws IOException {

//        String realPath = servletContext.getRealPath("/")+"/upload/banner";
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

            bannerService.save(bannerDTO.toEntity());
        }
        return files;
    }
//    @PostMapping("/delete")
//    public void delete(String uploadPath, String fileName, String uuid){
//        File file = new File("C:/upload", uploadPath + "/" + fileName);
//        BannerDTO bannerDTO = bannerService.selectByUuid(uuid);
//
//        if(file.exists()){
//            file.delete();
//            bannerService.delete(bannerDTO.toEntity());
//        }
//    }
    public String createDirectoryByNow(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date now = new Date();
        return format.format(now);
    }
}
