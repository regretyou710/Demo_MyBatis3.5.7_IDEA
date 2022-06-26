import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tw.com.mybatis.mapper.EmpMapper;
import tw.com.mybatis.pojo.Emp;
import tw.com.mybatis.utils.SqlSessionUtils;

import java.util.List;

public class ResultMapTest {

    /**
     * 解決欄位名和屬性名不一致的情況:
     * a>為欄位起別名,保持和屬性名的一致
     * b>設置全局配置,將_自動映射為駝峰
     * <setting name="mapUnderscoreToCamelCase" value="true"/>
     */
    @Test
    public void testGetAllEmp() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> emps = mapper.getAllEmp();
        emps.forEach(emp -> System.out.println(emp));
    }
}
