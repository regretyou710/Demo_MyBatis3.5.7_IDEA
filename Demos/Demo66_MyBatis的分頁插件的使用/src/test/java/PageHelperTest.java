import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tw.com.mybatis.mapper.EmpMapper;
import tw.com.mybatis.pojo.Emp;
import tw.com.mybatis.utils.SqlSessionUtils;

import java.util.Comparator;
import java.util.List;

public class PageHelperTest {

    /**
     * index:當前頁的起始索引
     * pageSize:每頁顯示的條數
     * pageNum:當前頁的頁碼
     * index=(pageNum-1)*pageSize
     * <p>
     * 使用MyBatis的分頁插件實現分頁功能:
     * 1. 需要在查詢功能之前開啟分頁
     * PageHelper.startPage(int pageNumn, int pageSize);
     * 2. 在查詢功能之後獲取分頁相關訊息
     * PageInfo<> page = new PageInfo<>(List<> list, int navigatePages)
     * list表示分頁數據
     * naviagetPages表示當前導航分頁的數量,通常為奇數
     *
     * 常用數據：
     * pageNum：當前頁的頁碼
     * pageSize：每頁顯示的條數
     * size：當前頁顯示的真實條數
     * total：總記錄數
     * pages：總頁數
     * prePage：上一頁的頁碼
     * nextPage：下一頁的頁碼
     * isFirstPage/isLastPage：是否為第一頁/最後一頁
     * hasPreviousPage/hasNextPage：是否存在上一頁/下一頁
     * navigatePages：導航分頁的頁碼數
     * navigatepageNums：導航分頁的頁碼，[1,2,3,4,5]
     */
    @Test
    public void testPageHelper01() {
        //獲取分頁訊息方式一:PageHelper.startPage()返回Page<>
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Page<Object> pageInfo = PageHelper.startPage(2, 4);
        List<Emp> emps = mapper.selectByExample(null);

        emps.sort(new Comparator<Emp>() {
            @Override
            public int compare(Emp x, Emp y) {
                return x.getEid().compareTo(y.getEid());
            }
        });

        emps.forEach(emp -> System.out.println(emp));
        System.out.println("---------------");
        System.out.println(pageInfo);
        /**
         * DEBUG 07-02 10:15:28,433 Cache Hit Ratio [SQL_CACHE]: 0.0 (LoggingCache.java:60)
         * DEBUG 07-02 10:15:28,480 ==>  Preparing: SELECT count(0) FROM T_EMP (BaseJdbcLogger.java:137)
         * DEBUG 07-02 10:15:28,576 ==> Parameters:  (BaseJdbcLogger.java:137)
         * DEBUG 07-02 10:15:28,650 <==      Total: 1 (BaseJdbcLogger.java:137)
         * DEBUG 07-02 10:15:28,653 ==>  Preparing: SELECT * FROM ( SELECT TMP_PAGE.*, ROWNUM PAGEHELPER_ROW_ID FROM ( select EID, EMP_NAME, AGE, SEX, EMAIL, DID from T_EMP ) TMP_PAGE) WHERE PAGEHELPER_ROW_ID <= ? AND PAGEHELPER_ROW_ID > ? (BaseJdbcLogger.java:137)
         * DEBUG 07-02 10:15:28,655 ==> Parameters: 8(Long), 4(Long) (BaseJdbcLogger.java:137)
         * DEBUG 07-02 10:15:28,669 <==      Total: 4 (BaseJdbcLogger.java:137)
         * Emp{eid=5, empName='emp5', age=30, sex='1', email='emp5@gmail.com', did=3}
         * Emp{eid=14, empName='emp2', age=23, sex='1', email='123@qq.com', did=null}
         * Emp{eid=15, empName='emp3', age=23, sex='1', email='123@qq.com', did=null}
         * Emp{eid=16, empName='emp1', age=23, sex='1', email='123@qq.com', did=null}
         * ---------------
         * Page{count=true, pageNum=2, pageSize=4, startRow=4, endRow=8, total=17, pages=5, reasonable=false, pageSizeZero=false}
         * [Emp{eid=5, empName='emp5', age=30, sex='1', email='emp5@gmail.com', did=3},
         * Emp{eid=14, empName='emp2', age=23, sex='1', email='123@qq.com', did=null},
         * Emp{eid=15, empName='emp3', age=23, sex='1', email='123@qq.com', did=null},
         * Emp{eid=16, empName='emp1', age=23, sex='1', email='123@qq.com', did=null}]
         */
    }

    @Test
    public void testPageHelper02() {
        //獲取分頁訊息方式二:透過PageInfo<>實例獲取
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        PageHelper.startPage(2, 4);
        List<Emp> emps = mapper.selectByExample(null);

        emps.sort(new Comparator<Emp>() {
            @Override
            public int compare(Emp x, Emp y) {
                return x.getEid().compareTo(y.getEid());
            }
        });

        PageInfo<Emp> pageInfo = new PageInfo<>(emps, 5);

        emps.forEach(emp -> System.out.println(emp));
        System.out.println("---------------");
        System.out.println(pageInfo);
        /**
         * DEBUG 07-02 10:23:49,214 Cache Hit Ratio [SQL_CACHE]: 0.0 (LoggingCache.java:60)
         * DEBUG 07-02 10:23:49,269 ==>  Preparing: SELECT count(0) FROM T_EMP (BaseJdbcLogger.java:137)
         * DEBUG 07-02 10:23:49,374 ==> Parameters:  (BaseJdbcLogger.java:137)
         * DEBUG 07-02 10:23:49,442 <==      Total: 1 (BaseJdbcLogger.java:137)
         * DEBUG 07-02 10:23:49,444 ==>  Preparing: SELECT * FROM ( SELECT TMP_PAGE.*, ROWNUM PAGEHELPER_ROW_ID FROM ( select EID, EMP_NAME, AGE, SEX, EMAIL, DID from T_EMP ) TMP_PAGE) WHERE PAGEHELPER_ROW_ID <= ? AND PAGEHELPER_ROW_ID > ? (BaseJdbcLogger.java:137)
         * DEBUG 07-02 10:23:49,446 ==> Parameters: 8(Long), 4(Long) (BaseJdbcLogger.java:137)
         * DEBUG 07-02 10:23:49,462 <==      Total: 4 (BaseJdbcLogger.java:137)
         * Emp{eid=5, empName='emp5', age=30, sex='1', email='emp5@gmail.com', did=3}
         * Emp{eid=14, empName='emp2', age=23, sex='1', email='123@qq.com', did=null}
         * Emp{eid=15, empName='emp3', age=23, sex='1', email='123@qq.com', did=null}
         * Emp{eid=16, empName='emp1', age=23, sex='1', email='123@qq.com', did=null}
         * ---------------
         * PageInfo{pageNum=2, pageSize=4, size=4, startRow=5, endRow=8, total=17, pages=5,
         *
         * list=Page{count=true, pageNum=2, pageSize=4, startRow=4, endRow=8, total=17, pages=5, reasonable=false, pageSizeZero=false}
         * [Emp{eid=5, empName='emp5', age=30, sex='1', email='emp5@gmail.com', did=3},
         * Emp{eid=14, empName='emp2', age=23, sex='1', email='123@qq.com', did=null},
         * Emp{eid=15, empName='emp3', age=23, sex='1', email='123@qq.com', did=null},
         * Emp{eid=16, empName='emp1', age=23, sex='1', email='123@qq.com', did=null}],
         *
         * prePage=1, nextPage=3, isFirstPage=false, isLastPage=false, hasPreviousPage=true, hasNextPage=true, navigatePages=5, navigateFirstPage=1, navigateLastPage=5, navigatepageNums=[1, 2, 3, 4, 5]}
         */
    }
}