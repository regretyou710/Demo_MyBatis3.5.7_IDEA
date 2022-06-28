package tw.com.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import tw.com.mybatis.pojo.Emp;

import java.util.List;

public interface DynamicSQLMapper {

    /**
     * 透過陣列實現批量刪除
     *
     * @param eids
     * @return
     */
    int deleteMoreByArray(@Param("eids") Integer[] eids);
}
