package persistence.mapper;

import org.apache.ibatis.annotations.*;
import persistence.dto.BoardDTO;

import java.util.List;

public interface BoardMapper {
    final String getAll="SELECT * FROM BOARD";
    //BoardDTO에 매핑할건데 property는 BoardDTO의 필드명이다
    @Select(getAll)
    @Results(id="boardResultSet",value={
            @Result(property = "id",column = "board_id"),
            @Result(property = "title",column = "title"),
            @Result(property = "writer",column = "writer"),
            @Result(property = "content",column = "content"),
            @Result(property = "regDate",column = "regDate"),
            @Result(property = "hit",column = "hit")
    })

    List<BoardDTO> getAll();//인터페이스 메소드

    @Select("SELECT * FROM BOARD WHERE BOARD_ID=#{id}")
    @ResultMap("boardResultSet")
    BoardDTO selectbyid(@Param("id") Long id);

    @SelectProvider(type=persistence.mapper.BoardSQL.class,method = "selectRecent")
    @ResultMap("boardResultSet")
    List<BoardDTO> selectRecentPost(int day);
    @Insert("insert into BOARD (title, writer, contents, regdate,hit)\n" +//꼭 명심해라 title 은 실제 db 컬럼명이고 #안에것은 가져온 파라미터값을 넣는것이다
            "values (#{title}, #{writer}, #{contents}, #{regDate}, #{hit})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(BoardDTO n);

    @Update("update BOARD set\n" +
            "        title = #{title},\n" +
            "        writer= #{writer},\n" +
            "        contents = #{contents},\n" +
            "        regdate = #{regDate},\n" +
            "        hit = #{hit}\n" +
            "        where board_id = #{id}")

    void update(BoardDTO n);
    @Delete("delete from BOARD where board_id = #{id}")
    void deleteselectone(Long id);

}
