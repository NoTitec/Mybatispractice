package service;

import persistence.dao.MyBoardDAO;
import persistence.dto.BoardDTO;

import java.time.LocalDateTime;
import java.util.List;

public class MyBoardService {
    private final MyBoardDAO boardDAO;//main에서 dao만들고 생성자로 가져옴

    public MyBoardService(MyBoardDAO boardDAO){this.boardDAO=boardDAO;}

    public List<BoardDTO> selectall(){//boarddao이용해 필요정보 가져옴
        List<BoardDTO> all=boardDAO.selectallwithannotation();
        return all;
    }
    public BoardDTO selectonewith_id(long num){
        BoardDTO one=boardDAO.selectonewithannotation(num);
        return one;
    }
    public List<BoardDTO> selectrecent_day(int day){
        List<BoardDTO> list=boardDAO.selectRecentWithannotation(day);
        return list;
    }
    public String insertnewboard(){//미리 준비된 자료 insert하는 메소드
        BoardDTO nd=new BoardDTO();
        nd.setTitle("new row");
        nd.setWriter("me");
        nd.setContents("wow2");
        nd.setRegDate(LocalDateTime.now());
        nd.setHit(0);
        boardDAO.insertBoard(nd);
        return "insert done!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
    }

    public String updateselectrow(long snum){//선택된 row를 미리 준비된 자료 update하는 메소드
        BoardDTO nd=new BoardDTO();
        nd.setId(snum);
        nd.setTitle("update row");
        nd.setWriter("me");
        nd.setContents("update wow");
        nd.setRegDate(LocalDateTime.now());
        nd.setHit(0);
        boardDAO.updateBoard(nd);
        return "update done!!!!!!!!!!!!!!!!!!!!!!!!";
    }

    public String deleteselectrow(long dnum){//선택된 row 를 삭제하는 메소드
        boardDAO.deleteBoard(dnum);
        return "delete done!!!!!!!!!!!!!!!!!!!!";
    }
}
