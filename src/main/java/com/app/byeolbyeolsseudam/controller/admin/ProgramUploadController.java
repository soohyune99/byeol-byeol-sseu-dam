package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.domain.banner.BannerDTO;
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
@RequestMapping("/program/*")
@RequiredArgsConstructor
@Slf4j
public class ProgramUploadController {

    @PostMapping("profile/upload")
    public List<ProgramDTO> uploadProfile(List<MultipartFile> upload) throws IOException {
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

    @PostMapping("detail/upload")
    public List<ProgramDTO> uploadDetail(List<MultipartFile> upload) throws IOException {
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














    public String createDirectoryByNow(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date now = new Date();
        return format.format(now);
    }

}
