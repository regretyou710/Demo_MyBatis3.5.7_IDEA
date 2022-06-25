package tw.com.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tw.com.mybatis.mapper.ParameterMapper;
import tw.com.mybatis.pojo.User;
import tw.com.mybatis.utils.SqlSessionUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterMapperTest {
    @Test
    public void testGetAllUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        List<User> allUser = mapper.getAllUser();
        allUser.forEach(user -> System.out.println(user));
    }

    /**
     * 在MyBatis獲取參數值有兩種方式:${}和#{}
     * ${}本質為字串拼接，需注意單引號及sql injection
     * #{}本質為佔位符賦值，會自動補上單引號
     * <p>
     * MyBatis獲取參數值的各種情況:
     * 1. mapper介面方法的參數為單個的字面量類型
     * 可以透過${}和#{}以任意的名稱獲取參數值，但是需要注意${}的單引號問題
     * 2. mapper介面方法的參數為多個時
     * 此時MyBatis會將這些參數放在一個map集合中，以兩種方式進行存儲
     * a>以arg0,arg1...為鍵,以參數為值
     * b>以param1,param2...為鍵,以參數為值
     * 因此只需要通過#{}和${}以鍵的方式訪問值即可,但是需要注意${}的單引號問題
     * 3. 若mapper介面方法的參數有多個時,可以手動將這些參數放在一個map中存儲
     * 只需要通過#{}和${}以鍵的方式訪問值即可,但是需要注意${}的單引號問題
     * 4. mapper介面方法的參數是實體類類型的參數
     * 只需要通過#{}和${}以屬性的方式訪問屬性值即可,但是需要注意${}的單引號問題
     * 5. 使用@Param註解命名參數
     * 此時MyBatis會將這些參數放在一個map集合中,以兩種方式進行存儲
     * a>以@Param註解的值為鍵,以參數為值
     * b>以param1,param2...為鍵,以參數為值
     * 因此只需要通過#{}和${}以鍵的方式訪問值即可,但是需要注意${}的單引號問題
     */
    @Test
    public void testCheckLoginByParam() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.checkLoginByParam("admin", "0000");
        System.out.println(user);
    }

    @Test
    public void testInsertUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        int rs = mapper.insertUser(new User(8, "李四", "4444", 20, "1", "李四@qq.com"));
        System.out.println(rs);
    }

    @Test
    public void testCheckLoginByMap() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("username", "admin");
        map.put("password", "0000");
        User user = mapper.checkLoginByMap(map);
        System.out.println(user);
    }

    @Test
    public void testCheckLogin() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.checkLogin("admin", "0000");
        System.out.println(user);
    }

    @Test
    public void testGetUserByUsername() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.getUserByUsername("admin");
        System.out.println(user);
    }

    @Test
    public void testJDBC() throws Exception {
        //JDBC操作數據庫方式

        final String URL = "jdbc:oracle:thin:@localhost:1521:orcl12c";
        Connection conn = DriverManager.getConnection(URL, "system", "manager");
        PreparedStatement ps = conn.prepareStatement("select * from t_user where id = ?");
        ps.setString(1, "1");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String username = rs.getString("USERNAME");
            String password = rs.getString("password");
            Integer age = rs.getInt(4);
            System.out.println("username:" + username);
            System.out.println("password:" + password);
            System.out.printf("age:%s%n", age);
        }
    }
}
