package tw.com.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import tw.com.mybatis.pojo.Emp;

import java.util.List;

public interface DynamicSQLMapper {

    /**
     * 透過list集合實現批量添加
     *
     * @param emps
     * @return
     */
    int insertMoreByList(@Param("emps") List<Emp> emps);
}
