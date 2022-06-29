package tw.com.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import tw.com.mybatis.pojo.Emp;

public interface CacheMapper {

    Emp getEmpByEid(@Param("eid") Integer eid);

    void insertEmp(Emp emp);
}
