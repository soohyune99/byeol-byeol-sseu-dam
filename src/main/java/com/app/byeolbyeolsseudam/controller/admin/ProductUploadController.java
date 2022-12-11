package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
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
@RequestMapping("/product/*")
@RequiredArgsConstructor
@Slf4j
public class ProductUploadController {

    @PostMapping("profile/upload")
    public List<ProductDTO> uploadProfile(List<MultipartFile> upload) throws IOException {
        String rootPath = "C:/upload/product";
        String uploadFileName = null;
        List<ProductDTO> files = new ArrayList<>();

        File uploadPath = new File(rootPath, createDirectoryByNow());
        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }

        for (MultipartFile multipartFile : upload){
            ProductDTO productDTO = new ProductDTO();
            UUID uuid = UUID.randomUUID();
            String fileName = multipartFile.getOriginalFilename();
            uploadFileName = uuid.toString() + "_" + fileName;
            productDTO.setProductFileProfileName(fileName);
            productDTO.setProductFileProfileUuid(uuid.toString());
            productDTO.setProductFileProfilePath("/upload/product/" + createDirectoryByNow());

            File saveFile =new File(uploadPath, uploadFileName);
            multipartFile.transferTo(saveFile);

            files.add(productDTO);

        }
        return files;
    }

    @PostMapping("detail/upload")
    public List<ProductDTO> uploadDetail(List<MultipartFile> upload) throws IOException {
        String rootPath = "C:/upload/product";
        String uploadFileName = null;
        List<ProductDTO> files = new ArrayList<>();

        File uploadPath = new File(rootPath, createDirectoryByNow());
        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }

        for (MultipartFile multipartFile : upload){
            ProductDTO productDTO = new ProductDTO();
            UUID uuid = UUID.randomUUID();
            String fileName = multipartFile.getOriginalFilename();
            uploadFileName = uuid.toString() + "_" + fileName;
            productDTO.setProductFileDetailName(fileName);
            productDTO.setProductFileDetailUuid(uuid.toString());
            productDTO.setProductFileDetailPath("/upload/product/" + createDirectoryByNow());

            File saveFile =new File(uploadPath, uploadFileName);
            multipartFile.transferTo(saveFile);

            files.add(productDTO);

        }
        return files;
    }


    public String createDirectoryByNow(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date now = new Date();
        return format.format(now);
    }

}
