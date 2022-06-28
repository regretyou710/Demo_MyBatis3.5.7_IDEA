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
     * 2. where:
     * 當where標籤中有內容時,會自動生成where關鍵字,並且將內容多餘的and或or去掉
     * 當where標籤中沒有內容時,此時where標籤沒有任何效果
     * 注意:where標籤不能將其中內容後面多餘的and或or去掉
     * 3. trim:
     * 若標籤中有內容時:
     * prefix|suffix: 將trim標籤中內容前面或後面添加指定內容
     * prefixOverrides|suffixOverrides: 將trim標籤中內容前面或後面去掉指定內容
     * 若標籤中沒有內容時,trim標籤也沒有任何效果
     * 4. choose、when、otherwise,相當於if...else if...else
     * chooes:父標籤
     * when:if..else if..else中的if..else if...
     * otherwise:if..else if..else中的else
     * a>當前面的條件滿足時後面的條件就不再執行(眾多的條件只會一個被滿足,因此不需要加and),若所有條件都不滿足則執行其他條件
     * b>when至少要有一個,otherwise最多只能有一個
     * 5. foreach
     */
    @Test
    public void testGetEmpByChoose() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        int result = mapper.deleteMoreByArray(new Integer[]{ 11, 12});
        //Preparing: delete from t_emp where eid in ( ? , ? , ? )
        //Preparing: delete from t_emp where eid = ? or eid = ? or eid = ?

        System.out.println(result);
    }
}
