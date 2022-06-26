import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tw.com.mybatis.mapper.DeptMapper;
import tw.com.mybatis.mapper.EmpMapper;
import tw.com.mybatis.pojo.Dept;
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
     * b>association
     * c>分步查詢
     * <p>
     * 處理一對多的映射關係
     * a>collection
     * b>分步查詢
     */
    @Test
    public void testGetDeptAndEmp() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptAndEmp(1);
        System.out.println(dept);
    }

    @Test
    public void testGetEmpAndDeptByStep() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.getEmpAndDeptByStepOne(1);

        //case1:只輸出emp.getEmpName()
        //在開啟延遲加載後,獲取emp屬性值時不會對dept的sql語句進行查詢
        System.out.println(emp.getEmpName());
        System.out.println("--------------------------------");

        //case2:輸出emp.getEmpName()後再輸出emp.getDept()
        //開啟延遲加載:先查詢emp的sql語句並輸出emp.getEmpName(),再查詢dept的sql語句並輸出emp.getDept()
        //關閉延遲加載:先查詢emp的sql語句再查詢dept的sql語句,之後輸出emp.getEmpName()再輸出emp.getDept()
        System.out.println(emp.getDept());
    }

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
