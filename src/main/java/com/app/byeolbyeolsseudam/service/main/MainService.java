package com.app.byeolbyeolsseudam.service.main;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.domain.course.CourseDTO;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.repository.main.MainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    private final MainRepository mainRepository;

    public List<ProgramDTO> showProgram(){
        return mainRepository.getMainProgramList();
    }

    public List<ProductDTO> showProductKitchen(){
        return mainRepository.getMainProductKitchen();
    }
    public List<ProductDTO> showProductBath(){
        return mainRepository.getMainProductBath();
    }
    public List<ProductDTO> showProductLife(){
        return mainRepository.getMainProductLife();
    }

    public List<BoardDTO> showTopViewBoardList(){
        return mainRepository.getMainTopBoardList();
    }
    public List<BoardDTO> showBoardList(){
        return mainRepository.getMainBoardList();
    }
    public List<CourseDTO> showCourseList(){return mainRepository.getCourseList();}

}
