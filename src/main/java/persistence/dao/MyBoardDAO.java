package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.BoardDTO;

import java.util.List;
import java.util.Map;

public class MyBoardDAO {
    private SqlSessionFactory sqlSessionFactory=null;

    public MyBoardDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory=sqlSessionFactory;
    }
    //function 1
    public List<BoardDTO> selectall(){
        List<BoardDTO> list=null;
        SqlSession session = sqlSessionFactory.openSession();//session은 트랙젝션 , mybatis factory에서 session1개반환
        try{
            list = session.selectList("mapper.BoardMapper.selectAll");//config.xml의 mapper 필드의 xml파일에서 mapper.BoardMapper의 selectall sql가져옴
        }finally {
            session.close();
        }
        return list;
    }
    //function 2
    public List<BoardDTO> findpostWithTitlelike(Map<String,Object> params){//이름이 params인 map 파라미터
        List<BoardDTO> list=null;
        SqlSession session = sqlSessionFactory.openSession();//session은 트랙젝션 , mybatis factory에서 session1개반환
        try{
            list = session.selectList("mapper.BoardMapper.findPostWithTitleLike",params);
        }finally {
            session.close();
        }
        return list;
    }
    //insert function 1
    public void insertBoard(BoardDTO b){//이름이 b인 boarddto파라미터
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("mapper.BoardMapper.insertBoard",b);
            session.commit();
        }finally{
            session.close();
        }
    }
    //update function 1
    public void updateBoard(BoardDTO b){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.update("mapper.BoardMapper.updateBoard",b);
            session.commit();
        }finally{
            session.close();
        }
    }
    //delete function 1
    public void deleteBoard(long dnum){//이름이 dnum인 long파라미터
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.delete("mapper.BoardMapper.deleteBoard",dnum);
            session.commit();
        }finally{
            session.close();
        }
    }
}
