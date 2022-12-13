package com.app.byeolbyeolsseudam.service.community;

import com.app.byeolbyeolsseudam.domain.Criteria;
import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.entity.board.Board;
import com.app.byeolbyeolsseudam.repository.board.BoardRepository;
import com.app.byeolbyeolsseudam.repository.fileBoard.FileBoardRepository;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.type.BoardCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityServiceImpl implements CommunityService {
    private final BoardRepository boardRepository;
    private final FileBoardRepository fileBoardRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<BoardDTO> selectTopView(){
        return boardRepository.selectTopView();
    }

    @Override
    public List<BoardDTO> selectBoards(Criteria criteria){
        return boardRepository.selectBoards(criteria);
    }

//    @Override
//    public List<BoardDTO> selectBoardsofCategory(BoardCategory boardCategory){
//        return boardRepository.selectBoardsofCategory(boardCategory);
//    }
//
//    @Override
//    public List<BoardDTO> selectBoardsofKeyword(String keyword){
//        return boardRepository.selectBoardsofKeyword(keyword);
//    }

    @Override
    public BoardDTO readBoard(Long boardId){
        return boardRepository.readBoard(boardId);
    }

    @Override
    public void saveBoard(BoardDTO boardDTO) {
        Board board = boardDTO.toEntity();
        board.changeMember(memberRepository.findById(boardDTO.getMemberId()).get());
        if(Optional.ofNullable(boardDTO.getFiles()).isPresent()){
            board.changeFiles(boardDTO.getFiles().stream().map(file -> file.toEntity(board)).collect(Collectors.toList()));
        }
        boardRepository.save(board);
    }



    @Override
    public void updateBoard(BoardDTO boardDTO){
        Board board = boardRepository.findById(boardDTO.getBoardId()).get();
        if(Optional.ofNullable(board.getFiles()).isPresent()){
            board.getFiles().stream().forEach(file -> fileBoardRepository.delete(file));
        }
        if(Optional.ofNullable(boardDTO.getFiles()).isPresent()){
            board.changeFiles(boardDTO.getFiles().stream().map(file -> file.toEntity(board)).collect(Collectors.toList()));
        }
        board.update(boardDTO);
        boardRepository.save(board);
    }

    @Override
    public void deleteBoard(Long boardId){
        log.info("ServiceImpl의 boardId : " + boardId);
        Board board = boardRepository.findById(boardId).get();
        board.getFiles().stream().forEach(file -> fileBoardRepository.delete(file));
        boardRepository.delete(board);
    }

//    @Override
//    public List<BoardDTO> selectScrollBoards(Criteria criteria){
//        return boardRepository.selectScrollBoards(criteria);
//    }

    @Override
    public Long plusView(Long boardId){
        BoardDTO boardDTO = boardRepository.readBoard(boardId);
        boardDTO.setBoardView(boardDTO.getBoardView() + 1);
        Board board = boardRepository.findById(boardId).get();
        board.update(boardDTO);
        boardRepository.save(board);
        return boardId;
    }
}
