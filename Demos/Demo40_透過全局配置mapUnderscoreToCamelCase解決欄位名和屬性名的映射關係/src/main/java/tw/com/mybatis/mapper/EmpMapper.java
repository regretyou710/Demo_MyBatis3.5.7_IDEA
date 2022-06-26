package tw.com.mybatis.mapper;

import tw.com.mybatis.pojo.Emp;

import java.util.List;

public interface EmpMapper {
    /**
     * 查詢所有員工訊息
     */
    List<Emp> getAllEmp();
}