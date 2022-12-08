package com.app.byeolbyeolsseudam.service.community;

import com.app.byeolbyeolsseudam.domain.Criteria;
import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.entity.board.Board;
import com.app.byeolbyeolsseudam.repository.board.BoardRepository;
import com.app.byeolbyeolsseudam.repository.fileBoard.FileBoardRepository;
import com.app.byeolbyeolsseudam.type.BoardCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityServiceImpl implements CommunityService{
    private final BoardRepository boardRepository;
    private final FileBoardRepository fileBoardRepository;

    @Override
    public List<BoardDTO> selectTopView(){
        return boardRepository.selectTopView();
    }

    @Override
    public List<BoardDTO> selectBoards(){
        return boardRepository.selectBoards();
    }

    @Override
    public List<BoardDTO> selectBoardsofCategory(BoardCategory boardCategory){
        return boardRepository.selectBoardsofCategory(boardCategory);
    }

    @Override
    public List<BoardDTO> selectBoardsofKeyword(String keyword){
        return boardRepository.selectBoardsofKeyword(keyword);
    }

    @Override
    public BoardDTO readBoard(Long boardId){
        return boardRepository.readBoard(boardId);
    }

    @Override
    public void saveBoard(BoardDTO boardDTO) {
        Board board = boardDTO.toEntity();
        board.changeFiles(boardDTO.getFiles().stream().map(file -> file.toEntity(board)).collect(Collectors.toList()));
        boardRepository.save(board);
    }



    @Override
    public void updateBoard(BoardDTO boardDTO){
//        Board board = boardRepository.updateBoard(boardDTO);
//        try {
//            board.getFiles().stream().forEach(file -> fileBoardRepository.delete(file));
//            List<FileBoard> files = boardRepository.saveFilesofBoard(boardDTO, board);
//            board.changeFile(files);
//            boardRepository.save(board);
//            files.stream().forEach(file -> fileBoardRepository.save(file));
//        } catch(NullPointerException e){
//            boardRepository.save(board);
//        }
    }

    @Override
    public void deleteBoard(Long boardId){
        boardRepository.delete(boardRepository.findById(boardId).get());
    }

    @Override
    public List<BoardDTO> selectScrollBoards(Criteria criteria){
        return boardRepository.selectScrollBoards(criteria);
    }

//    @Override
//    public void plusView(BoardDTO boardDTO){
//        Board board = boardRepository.plusView(boardDTO);
//        boardRepository.save(board);
//    }
}
