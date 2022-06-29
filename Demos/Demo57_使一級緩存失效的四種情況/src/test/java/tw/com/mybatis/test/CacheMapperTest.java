package tw.com.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tw.com.mybatis.mapper.CacheMapper;
import tw.com.mybatis.pojo.Emp;
import tw.com.mybatis.utils.SqlSessionUtils;

public class CacheMapperTest {

    /**
     * 1. 緩存只對select有作用
     * 2. 一級緩存是SqlSession級別的,通過同一個SqlSession查詢的數據會被緩存,下次查詢相同的數據,就會從緩存中直接獲取,不會從數據庫重新訪問
     * 3. 不同的mapper還是從透過同一個SqlSession查詢過的緩存來獲取
     * 4. 使一級緩存失效的四種情況:
     * a>不同的SqlSession對應不同的一級緩存
     * b>同一個SqlSession但查詢條件不同
     * c>同一個SqlSession兩次查詢期間執行了任何一次增刪改操作
     * d>同一個SqlSession兩次查詢期間手動清空了緩存
     */
    @Test
    public void testCacheMapper1() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp emp1 = mapper.getEmpByEid(1);
        System.out.println(emp1);

        Emp emp2 = mapper.getEmpByEid(1);
        System.out.println(emp2);

        /**
         * DEBUG 06-29 19:15:08,386 ==>  Preparing: select * from t_emp where eid = ? (BaseJdbcLogger.java:137)
         * DEBUG 06-29 19:15:08,496 ==> Parameters: 1(Integer) (BaseJdbcLogger.java:137)
         * DEBUG 06-29 19:15:08,574 <==      Total: 1 (BaseJdbcLogger.java:137)
         * Emp{eid=1, empName='張三', age=23, sex='1', email='123@qq.com', dept=null}
         * Emp{eid=1, empName='張三', age=23, sex='1', email='123@qq.com', dept=null}
         */
    }

    @Test
    public void testCacheMapper2() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper1 = sqlSession.getMapper(CacheMapper.class);
        Emp emp1 = mapper1.getEmpByEid(1);
        System.out.println(emp1);

        CacheMapper mapper2 = sqlSession.getMapper(CacheMapper.class);
        Emp emp2 = mapper2.getEmpByEid(1);
        System.out.println(emp2);

        /**
         * DEBUG 06-29 19:18:49,165 ==>  Preparing: select * from t_emp where eid = ? (BaseJdbcLogger.java:137)
         * DEBUG 06-29 19:18:49,259 ==> Parameters: 1(Integer) (BaseJdbcLogger.java:137)
         * DEBUG 06-29 19:18:49,351 <==      Total: 1 (BaseJdbcLogger.java:137)
         * Emp{eid=1, empName='張三', age=23, sex='1', email='123@qq.com', dept=null}
         * Emp{eid=1, empName='張三', age=23, sex='1', email='123@qq.com', dept=null}
         */
    }

    @Test
    public void testCacheMapper3() {
        SqlSession sqlSession1 = SqlSessionUtils.getSqlSession();
        CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
        Emp emp1 = mapper1.getEmpByEid(1);
        System.out.println(emp1);

        SqlSession sqlSession2 = SqlSessionUtils.getSqlSession();
        CacheMapper mapper2 = sqlSession2.getMapper(CacheMapper.class);
        Emp emp2 = mapper2.getEmpByEid(1);
        System.out.println(emp2);

        /**
         * DEBUG 06-29 19:21:46,997 ==>  Preparing: select * from t_emp where eid = ? (BaseJdbcLogger.java:137)
         * DEBUG 06-29 19:21:47,091 ==> Parameters: 1(Integer) (BaseJdbcLogger.java:137)
         * DEBUG 06-29 19:21:47,184 <==      Total: 1 (BaseJdbcLogger.java:137)
         * Emp{eid=1, empName='張三', age=23, sex='1', email='123@qq.com', dept=null}
         * DEBUG 06-29 19:21:47,232 ==>  Preparing: select * from t_emp where eid = ? (BaseJdbcLogger.java:137)
         * DEBUG 06-29 19:21:47,232 ==> Parameters: 1(Integer) (BaseJdbcLogger.java:137)
         * DEBUG 06-29 19:21:47,232 <==      Total: 1 (BaseJdbcLogger.java:137)
         * Emp{eid=1, empName='張三', age=23, sex='1', email='123@qq.com', dept=null}
         */
    }

    @Test
    public void testCacheMapper4() {
        //同一個SqlSession兩次查詢期間執行了任何一次增刪改操作
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp emp1 = mapper.getEmpByEid(1);
        System.out.println(emp1);

        mapper.insertEmp(new Emp(null, "emp1", 15, "0", "emp1@qq.com"));

        Emp emp2 = mapper.getEmpByEid(1);
        System.out.println(emp2);

        /**
         * DEBUG 06-29 19:38:15,969 ==>  Preparing: select * from t_emp where eid = ? (BaseJdbcLogger.java:137)
         * DEBUG 06-29 19:38:16,076 ==> Parameters: 1(Integer) (BaseJdbcLogger.java:137)
         * DEBUG 06-29 19:38:16,154 <==      Total: 1 (BaseJdbcLogger.java:137)
         * Emp{eid=1, empName='張三', age=23, sex='1', email='123@qq.com', dept=null}
         *
         * DEBUG 06-29 19:38:16,154 ==>  Preparing: insert into t_emp values (null,?,?,?,?,null) (BaseJdbcLogger.java:137)
         * DEBUG 06-29 19:38:16,154 ==> Parameters: emp1(String), 15(Integer), 0(String), emp1@qq.com(String) (BaseJdbcLogger.java:137)
         * DEBUG 06-29 19:38:16,154 <==    Updates: 1 (BaseJdbcLogger.java:137)
         *
         * DEBUG 06-29 19:38:16,154 ==>  Preparing: select * from t_emp where eid = ? (BaseJdbcLogger.java:137)
         * DEBUG 06-29 19:38:16,154 ==> Parameters: 1(Integer) (BaseJdbcLogger.java:137)
         * DEBUG 06-29 19:38:16,154 <==      Total: 1 (BaseJdbcLogger.java:137)
         * Emp{eid=1, empName='張三', age=23, sex='1', email='123@qq.com', dept=null}
         */
    }

    @Test
    public void testCacheMapper5() {
        //同一個SqlSession兩次查詢期間執行了任何一次增刪改操作
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp emp1 = mapper.getEmpByEid(1);
        System.out.println(emp1);

        sqlSession.clearCache();

        Emp emp2 = mapper.getEmpByEid(1);
        System.out.println(emp2);

        /**
         * DEBUG 06-29 19:42:49,136 ==>  Preparing: select * from t_emp where eid = ? (BaseJdbcLogger.java:137)
         * DEBUG 06-29 19:42:49,229 ==> Parameters: 1(Integer) (BaseJdbcLogger.java:137)
         * DEBUG 06-29 19:42:49,310 <==      Total: 1 (BaseJdbcLogger.java:137)
         * Emp{eid=1, empName='張三', age=23, sex='1', email='123@qq.com', dept=null}
         *
         * DEBUG 06-29 19:42:49,323 ==>  Preparing: select * from t_emp where eid = ? (BaseJdbcLogger.java:137)
         * DEBUG 06-29 19:42:49,323 ==> Parameters: 1(Integer) (BaseJdbcLogger.java:137)
         * DEBUG 06-29 19:42:49,323 <==      Total: 1 (BaseJdbcLogger.java:137)
         * Emp{eid=1, empName='張三', age=23, sex='1', email='123@qq.com', dept=null}
         */
    }
}
