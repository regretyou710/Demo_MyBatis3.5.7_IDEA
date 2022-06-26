package tw.com.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import tw.com.mybatis.pojo.Dept;

public interface DeptMapper {

    /**
     * 透過分步查詢查詢員工及員工所對應的部門訊息
     * 分步查詢第二步:透過did查詢員工所對應的部門訊息
     */
    Dept getEmpAndDeptByStepTwo(@Param("did") Integer did);

    /**
     * 獲取部門及部門中所有的員工訊息
     */
    Dept getDeptAndEmp(@Param("did") Integer did);
}