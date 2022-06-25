package tw.com.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tw.com.mybatis.mapper.SelectMapper;
import tw.com.mybatis.pojo.User;
import tw.com.mybatis.utils.SqlSessionUtils;

public class SelectMapperTest {
    /**
     * MyBatis的各種查詢功能:
     * 1. 若查詢出的數據只有一條
     * a>可以透過實體類對象接收
     * b>可以透過list集合接收
     * c>可以透過map集合接收
     * 結果:{PASSWORD=123456, SEX=1, USERNAME=admin2, ID=2, EMAIL=123456@qq.com, AGE=23}
     * 2. 若查詢出的數據有多條
     * a>可以透過實體類類型的list集合接收
     * b>可以透過map類型的list集合接收
     * c>可以在mapper介面的方法上添加@MapKey註解,此時就可以將每條數據轉換的map集合作為值,以某個欄位的值作為鍵,放在同一個map集合中
     * 注意:一定不能透過實體類對象接收,此時會拋異常TooManyResultsException
     *
     * MyBatis中設置了默認的類型別名
     * java.lang.Integer-->int,integer
     * int-->_int,_integer
     * Map-->map
     * String-->string
     * ...
     */
    @Test
    public void testGetAllUserToMap() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getAllUserToMap());
    }

    @Test
    public void testGetUserByIdToMap() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getUserByIdToMap(2));
    }

    @Test
    public void testGetCount() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getCount());
    }

    @Test
    public void testGetAllUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getAllUser());
    }

    @Test
    public void testGetUserById() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getUserById(2));
    }
}
