package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.BoardDTO;
import persistence.mapper.BoardMapper;
import persistence.mapper.BoardSQL;

import java.util.List;
import java.util.Map;

public class MyBoardDAO {
    private SqlSessionFactory sqlSessionFactory=null;

    public MyBoardDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory=sqlSessionFactory;
    }
    //select function 1
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
    //select function 2
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
    //select function with annotation 1
    public List<BoardDTO> selectallwithannotation() {
        List<BoardDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();//session은 트랙젝션 , mybatis factory에서 session1개반환
        BoardMapper mapper= session.getMapper(BoardMapper.class);//factory에 알려준거 꺼내씀
        try {
            list = mapper.getAll();//mapperr객체 getall메소드실행
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        finally {
            session.close();
        }
        return list;
    }
    //select function 2 with annotation
    public BoardDTO selectonewithannotation(long id) {
        BoardDTO one = null;
        SqlSession session = sqlSessionFactory.openSession();//session은 트랙젝션 , mybatis factory에서 session1개반환
        BoardMapper mapper= session.getMapper(BoardMapper.class);//factory에 알려준거 꺼내씀
        try {
            one = mapper.selectbyid(id);//mapperr객체 getall메소드실행
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
            session.rollback();
        }
        finally {
            session.close();
        }
        return one;
    }
    //select dynamic annotation 1
    public List<BoardDTO> selectRecentWithannotation(int day){
        List<BoardDTO> list=null;
        SqlSession session = sqlSessionFactory.openSession();//session은 트랙젝션 , mybatis factory에서 session1개반환
        BoardMapper mapper= session.getMapper(BoardMapper.class);
        try{
            list=mapper.selectRecentPost(day);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return list;
    }
    //insert function 1
    public void insertBoard(BoardDTO b){//이름이 b인 boarddto파라미터
        SqlSession session = sqlSessionFactory.openSession();
        BoardMapper mapper= session.getMapper(BoardMapper.class);
        try {
            //session.insert("mapper.BoardMapper.insertBoard",b);
            mapper.insert(b);
            session.commit();//mybatis는 기본적으로 commit false라 명시적으로 commit이나 rollback설정을 해줘야 한다
        }finally{
            session.close();
        }
    }
    //update function 1
    public void updateBoard(BoardDTO b){
        SqlSession session = sqlSessionFactory.openSession();
        BoardMapper mapper = session.getMapper(BoardMapper.class);
        try {
            //session.update("mapper.BoardMapper.updateBoard",b);
            mapper.update(b);
            session.commit();
        }finally{
            session.close();
        }
    }
    //delete function 1
    public void deleteBoard(long dnum){//이름이 dnum인 long파라미터
        SqlSession session = sqlSessionFactory.openSession();
        BoardMapper mapper= session.getMapper(BoardMapper.class);
        try {
            mapper.deleteselectone(dnum);
            session.commit();

        }catch (Exception e){

            e.printStackTrace();
            session.rollback();
        }
        finally{
            session.close();
        }
    }
}
