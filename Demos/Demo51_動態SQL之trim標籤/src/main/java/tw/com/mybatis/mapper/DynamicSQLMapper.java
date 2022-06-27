package tw.com.mybatis.mapper;

import tw.com.mybatis.pojo.Emp;

import java.util.List;

public interface DynamicSQLMapper {

    /**
     * 多條件查詢
     *
     * @param emp
     * @return
     */
    List<Emp> getEmpByCondition(Emp emp);
}
