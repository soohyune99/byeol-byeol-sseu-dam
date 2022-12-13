package com.app.byeolbyeolsseudam.repository.main;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.domain.board.QBoardDTO;
import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.comment.QCommentDTO;
import com.app.byeolbyeolsseudam.domain.course.CourseDTO;
import com.app.byeolbyeolsseudam.domain.course.QCourseDTO;
import com.app.byeolbyeolsseudam.domain.fileBoard.FileBoardDTO;
import com.app.byeolbyeolsseudam.domain.fileBoard.QFileBoardDTO;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.domain.product.QProductDTO;
import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.domain.program.QProgramDTO;
import com.app.byeolbyeolsseudam.entity.board.QBoard;
import com.app.byeolbyeolsseudam.entity.fileBoard.QFileBoard;
import com.app.byeolbyeolsseudam.entity.product.QProduct;
import com.app.byeolbyeolsseudam.entity.program.QProgram;
import com.app.byeolbyeolsseudam.type.ProductCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.board.QBoard.board;
import static com.app.byeolbyeolsseudam.entity.comment.QComment.comment;
import static com.app.byeolbyeolsseudam.entity.course.QCourse.course;
import static com.app.byeolbyeolsseudam.entity.fileBoard.QFileBoard.fileBoard;
import static com.app.byeolbyeolsseudam.entity.product.QProduct.product;
import static com.app.byeolbyeolsseudam.entity.program.QProgram.program;

@Repository
@RequiredArgsConstructor
public class MainRepository {
    private final JPAQueryFactory jpaQueryFactory;

    //메인페이지 프로그램 목록 - 별별수강
    public List<ProgramDTO> getMainProgramList(){
        return jpaQueryFactory.select(new QProgramDTO(
                program.programId,
                program.programName,
                program.programPlace,
                program.possibleDate.openingDate,
                program.possibleDate.closingDate,
                program.programTime,
                program.programDate,
                program.programContent,
                program.programLimitCount,
                program.programStatus,
                program.programFileProfileName,
                program.programFileProfilePath,
                program.programFileProfileUuid,
                program.programFileDetailName,
                program.programFileDetailPath,
                program.programFileDetailUuid,
                program.createdDate
        )).from(program).orderBy(program.programId.desc()).limit(8).fetch();
    }
    //메인페이지 상품 목록 - 주방
    public List<ProductDTO> getMainProductKitchen(){
        return jpaQueryFactory.select(new QProductDTO(
                product.productId,product.productCategory, product.productName,
                product.productPrice,product.productCount,product.productFileDetailName,
                product.productFileDetailPath,product.productFileDetailUuid,product.productFileProfileName,
                product.productFileProfilePath,product.productFileProfileUuid
        )).from(product)
                .where(product.productCategory.eq(ProductCategory.주방))
                .orderBy(product.productId.desc())
                .limit(6)
                .fetch();
    }
    //메인페이지 상품 목록 - 욕실
    public List<ProductDTO> getMainProductBath(){
        return jpaQueryFactory.select(new QProductDTO(
                product.productId,product.productCategory, product.productName,
                product.productPrice,product.productCount,product.productFileDetailName,
                product.productFileDetailPath,product.productFileDetailUuid,product.productFileProfileName,
                product.productFileProfilePath,product.productFileProfileUuid
        )).from(product)
                .where(product.productCategory.eq(ProductCategory.욕실))
                .orderBy(product.productId.desc())
                .limit(6)
                .fetch();
    }
    //메인페이지 상품 목록 - 생활
    public List<ProductDTO> getMainProductLife(){
        return jpaQueryFactory.select(new QProductDTO(
                product.productId,product.productCategory, product.productName,
                product.productPrice,product.productCount,product.productFileDetailName,
                product.productFileDetailPath,product.productFileDetailUuid,product.productFileProfileName,
                product.productFileProfilePath,product.productFileProfileUuid
        )).from(product)
                .where(product.productCategory.eq(ProductCategory.생활))
                .orderBy(product.productId.desc())
                .limit(6)
                .fetch();
    }
    //메인페이지 게시글 목록 - 가장 뜨거운 게시물
    public List<BoardDTO> getMainTopBoardList(){

        List<BoardDTO> boards = jpaQueryFactory.select(new QBoardDTO(board.boardId, board.boardCategory,
                board.boardTitle, board.boardContent, board.boardView, board.member.memberId,
                board.member.memberName, board.member.memberProfileName, board.member.memberProfilePath,
                board.member.memberProfileUuid, board.createdDate, board.updatedDate))
                .from(board)
                .orderBy(board.boardView.desc())
                .limit(8)
                .fetch();

        boards.stream().forEach(board ->{
            List<CommentDTO> comments = jpaQueryFactory.select(new QCommentDTO(
                    comment.commentId, comment.commentContent, comment.commentFileName, comment.commentFilePath,
                    comment.commentFileUuid, comment.member.memberId, comment.member.memberName,
                    comment.member.memberProfileName, comment.member.memberProfilePath, comment.member.memberProfileUuid,
                    comment.board.boardId, comment.createdDate, comment.updatedDate))
                    .from(comment)
                    .where(comment.board.boardId.eq(board.getBoardId()))
                    .fetch();
            board.setComments(comments);
        });
        return boards;
    }
    //메인페이지 게시글 목록 - 커뮤니티에 물어보세요
    public List<BoardDTO> getMainBoardList(){
        List<BoardDTO> boards =  jpaQueryFactory.select(new QBoardDTO(
                board.boardId, board.boardCategory,
                board.boardTitle, board.boardContent, board.boardView, board.member.memberId,
                board.member.memberName, board.member.memberProfileName, board.member.memberProfilePath,
                board.member.memberProfileUuid, board.createdDate,board.updatedDate
        )).from(board).orderBy(board.boardId.desc()).limit(6).fetch();

        boards.stream().forEach(board ->{
            List<CommentDTO> comments = jpaQueryFactory.select(new QCommentDTO(
                    comment.commentId, comment.commentContent, comment.commentFileName, comment.commentFilePath,
                    comment.commentFileUuid, comment.member.memberId, comment.member.memberName,
                    comment.member.memberProfileName, comment.member.memberProfilePath, comment.member.memberProfileUuid,
                    comment.board.boardId, comment.createdDate, comment.updatedDate))
                    .from(comment)
                    .where(comment.board.boardId.eq(board.getBoardId()))
                    .fetch();
            board.setComments(comments);
        });

        boards.stream().forEach(board -> {
            List<FileBoardDTO> files = jpaQueryFactory.select(new QFileBoardDTO(
                    fileBoard.fileBoardName
            )).from(fileBoard)
                    .where(fileBoard.board.boardId.eq(board.getBoardId()))
                    .fetch();
            board.setFiles(files);
        });

        return boards;
    }

    public List<CourseDTO> getCourseList(){
        return jpaQueryFactory.select(new QCourseDTO(
                course.courseId,
                course.courseName,
                course.courseArea,
                course.courseDistance,
                course.courseTime,
                course.courseStart,
                course.courseFinish,
                course.courseGrade,
                course.courseFileName,
                course.courseFilePath,
                course.courseFileUuid,
                course.possibleDate.openingDate,
                course.possibleDate.closingDate
        )).from(course)
                .orderBy(course.courseName.asc())
                .fetch();
    }


}
