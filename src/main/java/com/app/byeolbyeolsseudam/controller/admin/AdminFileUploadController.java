package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import com.app.byeolbyeolsseudam.domain.banner.BannerDTO;
import com.app.byeolbyeolsseudam.domain.course.CourseDTO;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.domain.spot.SpotDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file/*")
@RequiredArgsConstructor
@Slf4j
public class AdminFileUploadController {

//배너
    @PostMapping("/banner/upload")
    public List<BannerDTO> uploadBanner(List<MultipartFile> upload) throws IOException {
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
    @PostMapping("/banner/delete")
    public void deleteBanner(String uploadPath,String bannerUuid, String bannerName){
        File file = new File("C:/", uploadPath+"/"+bannerUuid+"_"+bannerName);

        if(file.exists()){
            file.delete();
        }
    }
//제품 대표 이미지
    @PostMapping("/product/profile/upload")
    public List<ProductDTO> uploadProductProfile(List<MultipartFile> upload) throws IOException {
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
// 제품 상세 이미지
    @PostMapping("/product/detail/upload")
    public List<ProductDTO> uploadProductDetail(List<MultipartFile> upload) throws IOException {
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

//  프로그램 대표 이미지
    @PostMapping("/program/profile/upload")
    public List<ProgramDTO> uploadProgramProfile(List<MultipartFile> upload) throws IOException {
        String rootPath = "C:/upload/program";
        String uploadFileName = null;
        List<ProgramDTO> files = new ArrayList<>();

        File uploadPath = new File(rootPath, createDirectoryByNow());
        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }

        for (MultipartFile multipartFile : upload){
            ProgramDTO programDTO = new ProgramDTO();
            UUID uuid = UUID.randomUUID();
            String fileName = multipartFile.getOriginalFilename();
            uploadFileName = uuid.toString() + "_" + fileName;
            programDTO.setProgramFileProfileName(fileName);
            programDTO.setProgramFileProfileUuid(uuid.toString());
            programDTO.setProgramFileProfilePath("/upload/program/" + createDirectoryByNow());

            File saveFile =new File(uploadPath, uploadFileName);
            multipartFile.transferTo(saveFile);

            files.add(programDTO);

        }
        return files;
    }
// 프로그램 상세 이미지
    @PostMapping("/program/detail/upload")
    public List<ProgramDTO> uploadProgramDetail(List<MultipartFile> upload) throws IOException {
        String rootPath = "C:/upload/program";
        String uploadFileName = null;
        List<ProgramDTO> files = new ArrayList<>();

        File uploadPath = new File(rootPath, createDirectoryByNow());
        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }

        for (MultipartFile multipartFile : upload){
            ProgramDTO programDTO = new ProgramDTO();
            UUID uuid = UUID.randomUUID();
            String fileName = multipartFile.getOriginalFilename();
            uploadFileName = uuid.toString() + "_" + fileName;
            programDTO.setProgramFileDetailName(fileName);
            programDTO.setProgramFileDetailUuid(uuid.toString());
            programDTO.setProgramFileDetailPath("/upload/program/" + createDirectoryByNow());

            File saveFile =new File(uploadPath, uploadFileName);
            multipartFile.transferTo(saveFile);

            files.add(programDTO);

        }
        return files;
    }
// 줍깅 코스 이미지
    @PostMapping("/course/upload")
    public List<CourseDTO> uploadCourseProfile(List<MultipartFile> upload) throws IOException {
        String rootPath = "C:/upload/course";
        String uploadFileName = null;
        List<CourseDTO> files = new ArrayList<>();

        File uploadPath = new File(rootPath, createDirectoryByNow());
        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }

        for (MultipartFile multipartFile : upload){
            CourseDTO courseDTO = new CourseDTO();
            UUID uuid = UUID.randomUUID();
            String fileName = multipartFile.getOriginalFilename();
            uploadFileName = uuid.toString() + "_" + fileName;
            courseDTO.setCourseFileName(fileName);
            courseDTO.setCourseFileUuid(uuid.toString());
            courseDTO.setCourseFilePath("/upload/course/" + createDirectoryByNow());

            File saveFile =new File(uploadPath, uploadFileName);
            multipartFile.transferTo(saveFile);

            files.add(courseDTO);

        }
        return files;
    }


    //배지 이미지
    @PostMapping("/badge/upload")
    public List<BadgeDTO> uploadBadge(List<MultipartFile> upload) throws IOException {
        String rootPath = "C:/upload/badge";
        String uploadFileName = null;
        List<BadgeDTO> files = new ArrayList<>();

        File uploadPath = new File(rootPath, createDirectoryByNow());
        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }

        for (MultipartFile multipartFile : upload){
            BadgeDTO badgeDTO = new BadgeDTO();
            String uuid = UUID.randomUUID().toString();
            String fileName = multipartFile.getOriginalFilename();
            uploadFileName = uuid + fileName;
            badgeDTO.setBadgeFileName("/upload/badge/" + createDirectoryByNow()+ "/" + uuid + fileName);

            File saveFile =new File(uploadPath, uploadFileName);
            multipartFile.transferTo(saveFile);

            files.add(badgeDTO);

        }
        return files;
    }

    @PostMapping("/qr/download/{path}")
    public ResponseEntity<Resource> generateQr(@PathVariable(name = "path") String path) throws UnsupportedEncodingException {
        log.info("컨트롤러");
        log.info(path);
        Resource resource = new FileSystemResource(path);
//URI uri = ClassLoader.getSystemResource("com/stackoverflow/json").toURI();
//String mainPath = Paths.get(uri).toString();
//Path path = Paths.get(mainPath ,"Movie.class");
        HttpHeaders header = new HttpHeaders();
        String name = resource.getFilename();
//        name = name.substring(name.indexOf("_")+1);
        header.add("Content-Disposition","attachment;filename="+ new String(name.getBytes("UTF-8")));

        return new ResponseEntity<>(resource,header, HttpStatus.OK);
    }






//    @PostMapping("/qr/upload")
//    public List<SpotDTO> uploadQr(List<MultipartFile> upload) throws IOException {
//        String rootPath = "C:/upload/spot";
//        String uploadFileName = null;
//        List<SpotDTO> files = new ArrayList<>();
//
//        File uploadPath = new File(rootPath, createDirectoryByNow());
//        if(!uploadPath.exists()){
//            uploadPath.mkdirs();
//        }
//
//        for (MultipartFile multipartFile : upload){
//            SpotDTO spotDTO = new SpotDTO();
//            String uuid = UUID.randomUUID().toString();
//            String fileName = multipartFile.getOriginalFilename();
//            uploadFileName = uuid + fileName;
//            spotDTO.setSpotQrName("/upload/spot/" + createDirectoryByNow()+ "/" + uuid + fileName);
//
//            File saveFile =new File(uploadPath, uploadFileName);
//            multipartFile.transferTo(saveFile);
//
//            files.add(spotDTO);
//
//        }
//        return files;
//    }


    public String createDirectoryByNow(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date now = new Date();
        return format.format(now);
    }
}