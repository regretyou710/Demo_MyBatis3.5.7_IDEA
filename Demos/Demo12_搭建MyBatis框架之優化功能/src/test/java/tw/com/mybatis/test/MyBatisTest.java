package tw.com.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.BasicConfigurator;
import org.junit.Test;
import tw.com.mybatis.mapper.UserMapper;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {

    /**
     * SqlSession默認不自動提交事務，若需要自動提交事務
     * 可以使用SqlSessionFactory.openSession(true);
     */
    @Test
    public void testMyBatis() throws IOException {
        BasicConfigurator.configure();

        //加載核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        //獲取SqlSessionFactoryBulider
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        //獲取SqlSessionFactory
        SqlSessionFactory SqlSessionFactory = sqlSessionFactoryBuilder.build(is);

        //獲取SqlSession(代表Java程序和數據庫之間的會話)
        SqlSession sqlSession = SqlSessionFactory.openSession(true);

        //獲取mapper介面對象(底層透過代理模式獲取)
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //測試方法
        int result = mapper.insertUser();

        //因為核心配置文件中交易事務管理器是設定JDBC(使用原始的JDBC管理事務，所有事務的提交或回滾都要手動處理)
        //sqlSession.commit();

        System.out.println(result);
    }
}
