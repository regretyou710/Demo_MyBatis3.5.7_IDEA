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
     * c>透過resultMap設置自訂義的映射關係
     * <resultMap id="empResultMap" type="Emp">
     * <id property="eid" column="eid"></id>
     * <result property="empName" column="emp_name"></result>
     * <result property="age" column="age"></result>
     * <result property="sex" column="sex"></result>
     * <result property="email" column="email"></result>
     * </resultMap>
     * <p>
     * 處理多對一的映射關係:
     * a>級聯屬性賦值
     */
    @Test
    public void testGetEmpAndDept() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.getEmpAndDept(1);
        System.out.println(emp);
    }

    @Test
    public void testGetAllEmp() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> emps = mapper.getAllEmp();
        emps.forEach(emp -> System.out.println(emp));
    }
}
