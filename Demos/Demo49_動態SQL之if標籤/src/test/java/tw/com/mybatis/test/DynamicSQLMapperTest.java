package tw.com.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tw.com.mybatis.mapper.DynamicSQLMapper;
import tw.com.mybatis.pojo.Emp;
import tw.com.mybatis.utils.SqlSessionUtils;

import java.util.List;

public class DynamicSQLMapperTest {

    /**
     * 動態SQL:
     * 1. if:根據標籤中test屬性所對應的表達式決定標籤中的內容是否需要拼接到SQL中
     */
    @Test
    public void testGetEmpByCondition() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> emps = mapper.getEmpByCondition(new Emp(null, "", null, "1", "123@qq.com"));
        System.out.println(emps);
    }
}
