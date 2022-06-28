package tw.com.mybatis.mapper;

import tw.com.mybatis.pojo.Emp;

import java.util.List;

public interface DynamicSQLMapper {

    /**
     * 測試choose、when、otherwise
     *
     * @param emp
     * @return
     */
    List<Emp> getEmpByChoose(Emp emp);
}
