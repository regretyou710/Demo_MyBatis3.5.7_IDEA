package tw.com.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import tw.com.mybatis.pojo.Emp;

import java.util.List;

public interface EmpMapper {
    /**
     * 查詢所有員工訊息
     */
    List<Emp> getAllEmp();

    /**
     * 查詢員工以及員工所對應的部門訊息
     */
    Emp getEmpAndDept(@Param("eid") Integer eid);
}