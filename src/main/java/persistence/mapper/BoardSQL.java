package persistence.mapper;

import org.apache.ibatis.jdbc.SQL;

public class BoardSQL {
    public String selectRecent(int day){
        SQL sql= new SQL(){//익명클래스
            {
                SELECT("*");
                FROM("BOARD");
                WHERE("DATEDIFF(NOW(),REGDATE)<#{day}");
            }
        };
        return sql.toString();
    }

}
