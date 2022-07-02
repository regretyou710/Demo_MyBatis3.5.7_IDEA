import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tw.com.mybatis.pojo.EmpExample;
import tw.com.mybatis.utils.SqlSessionUtils;

public class PageHelperTest {

    /**
     * index:當前頁的起始索引
     * pageSize:每頁顯示的條數
     * pageNum:當前頁的頁碼
     * index=(pageNum-1)*pageSize
     */
    @Test
    public void testPageHelper() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpExample mapper = sqlSession.getMapper(EmpExample.class);
    }
}