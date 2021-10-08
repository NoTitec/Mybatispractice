package persistence;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import persistence.mapper.BoardMapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

public class MybatisConnectionFactory {
    private static SqlSessionFactory sqlSessionFactory;
    static {//main메모리에 올라감 new 필요없음
        try {
            String resource = "config/config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader,"development");//config.xml 정보중 development enviroment정보로 로드
                Class[] mappers={
                        BoardMapper.class
                        //StudentMapper.class ...etc
                };
                for(Class mapper:mappers){
                    sqlSessionFactory.getConfiguration().addMapper(mapper);
                }
            }
        }
        catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
