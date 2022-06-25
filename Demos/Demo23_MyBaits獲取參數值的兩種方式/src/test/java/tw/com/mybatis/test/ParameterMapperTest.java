package tw.com.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tw.com.mybatis.mapper.ParameterMapper;
import tw.com.mybatis.pojo.User;
import tw.com.mybatis.utils.SqlSessionUtils;

import java.sql.*;
import java.util.List;

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
     */
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
