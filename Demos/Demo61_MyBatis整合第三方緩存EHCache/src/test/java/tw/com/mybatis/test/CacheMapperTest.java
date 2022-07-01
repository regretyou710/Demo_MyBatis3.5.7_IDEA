package tw.com.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import tw.com.mybatis.mapper.CacheMapper;
import tw.com.mybatis.pojo.Emp;
import tw.com.mybatis.utils.SqlSessionUtils;

import java.io.IOException;
import java.io.InputStream;

public class CacheMapperTest {

    /**
     * 二級緩存是SqlSessionFactory級別，通過同一個SqlSessionFactory創建的SqlSession查詢的結果會被
     * 緩存；此後若再次執行相同的查詢語句，結果就會從緩存中獲取
     * 二級緩存開啟的條件：
     * a>在核心配置文件中，設置全局配置屬性cacheEnabled="true"，默認為true，不需要設置
     * b>在映射文件中設置標籤<cache />
     * c>二級緩存必須在SqlSession關閉或提交之後有效(不包括openSession設置true)
     * d>查詢的數據所轉換的實體類類型必須實現序列化的接口
     * 使二級緩存失效的情況：
     * 兩次查詢之間執行了任意的增刪改，會使一級和二級緩存同時失效
     */
    @Test
    public void testCacheMapper() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

            SqlSession sqlSession1 = factory.openSession(true);
            CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
            Emp emp1 = mapper1.getEmpByEid(1);
            System.out.println(emp1);
            sqlSession1.close();

            SqlSession sqlSession2 = factory.openSession(true);
            CacheMapper mapper2 = sqlSession2.getMapper(CacheMapper.class);
            Emp emp2 = mapper2.getEmpByEid(1);
            System.out.println(emp2);
            sqlSession2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
