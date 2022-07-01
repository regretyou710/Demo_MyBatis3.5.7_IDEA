import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tw.com.mybatis.mapper.EmpMapper;
import tw.com.mybatis.pojo.Emp;
import tw.com.mybatis.pojo.EmpExample;
import tw.com.mybatis.utils.SqlSessionUtils;

import java.util.List;

public class Demo63Test {
    @Test
    public void test01() {
        //查詢所有數據
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> emps = mapper.selectByExample(null);
        emps.forEach(emp -> System.out.println(emp));
    }

    @Test
    public void test02() {
        //根據條件查詢
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

        //query by criteria風格
        EmpExample empExample = new EmpExample();
        empExample.createCriteria().andEmpNameEqualTo("張三");
        List<Emp> emps = mapper.selectByExample(empExample);
        emps.forEach(emp -> System.out.println(emp));
        /**
         * DEBUG 07-01 21:08:38,689 ==>  Preparing: select EID, EMP_NAME, AGE, SEX, EMAIL, DID from T_EMP WHERE ( EMP_NAME = ? ) (BaseJdbcLogger.java:137)
         * DEBUG 07-01 21:08:38,805 ==> Parameters: 張三(String) (BaseJdbcLogger.java:137)
         * DEBUG 07-01 21:08:38,896 <==      Total: 1 (BaseJdbcLogger.java:137)
         * Emp{eid=1, empName='張三', age=23, sex='1', email='123@qq.com', did=1}
         */

        EmpExample empExample2 = new EmpExample();
        empExample2.createCriteria().andEmpNameEqualTo("張三").andAgeGreaterThanOrEqualTo((short) 20);
        emps = mapper.selectByExample(empExample2);
        emps.forEach(emp -> System.out.println(emp));
        /**
         * DEBUG 07-01 22:12:39,515 ==>  Preparing: select EID, EMP_NAME, AGE, SEX, EMAIL, DID from T_EMP WHERE ( EMP_NAME = ? and AGE >= ? ) (BaseJdbcLogger.java:137)
         * DEBUG 07-01 22:12:39,516 ==> Parameters: 張三(String), 20(Short) (BaseJdbcLogger.java:137)
         * DEBUG 07-01 22:12:39,528 <==      Total: 1 (BaseJdbcLogger.java:137)
         * Emp{eid=1, empName='張三', age=23, sex='1', email='123@qq.com', did=1}
         */
    }

    @Test
    public void test03() {
        //根據條件查詢
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

        //query by criteria風格
        EmpExample empExample = new EmpExample();
        empExample.createCriteria().andEmpNameEqualTo("張三").andAgeGreaterThanOrEqualTo((short) 20);
        empExample.or().andDidIsNotNull();
        List<Emp> emps = mapper.selectByExample(empExample);
        emps.forEach(emp -> System.out.println(emp));
        /**
         * DEBUG 07-01 22:15:23,877 ==>  Preparing: select EID, EMP_NAME, AGE, SEX, EMAIL, DID from T_EMP WHERE ( EMP_NAME = ? and AGE >= ? ) or( DID is not null ) (BaseJdbcLogger.java:137)
         * DEBUG 07-01 22:15:23,986 ==> Parameters: 張三(String), 20(Short) (BaseJdbcLogger.java:137)
         * DEBUG 07-01 22:15:24,069 <==      Total: 5 (BaseJdbcLogger.java:137)
         * Emp{eid=1, empName='張三', age=23, sex='1', email='123@qq.com', did=1}
         * Emp{eid=2, empName='李四', age=17, sex='0', email='123@qq.com', did=2}
         * Emp{eid=3, empName='王五', age=30, sex='1', email='123@qq.com', did=3}
         * Emp{eid=4, empName='趙六', age=25, sex='0', email='123@qq.com', did=1}
         * Emp{eid=5, empName='田七', age=19, sex='1', email='123@qq.com', did=2}
         */
    }
}
