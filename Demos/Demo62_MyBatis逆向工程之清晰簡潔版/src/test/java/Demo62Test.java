import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tw.com.mybatis.mapper.EmpMapper;
import tw.com.mybatis.pojo.Emp;
import tw.com.mybatis.utils.SqlSessionUtils;

import java.util.List;

public class Demo62Test {
    @Test
    public void test() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> emps = mapper.selectAll();
        System.out.println(emps);
    }
}
