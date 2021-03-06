package tw.com.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import tw.com.mybatis.pojo.Emp;
import tw.com.mybatis.pojo.EmpExample;

public interface EmpMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_EMP
     *
     * @mbggenerated Fri Jul 01 20:35:36 CST 2022
     */
    int countByExample(EmpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_EMP
     *
     * @mbggenerated Fri Jul 01 20:35:36 CST 2022
     */
    int deleteByExample(EmpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_EMP
     *
     * @mbggenerated Fri Jul 01 20:35:36 CST 2022
     */
    int deleteByPrimaryKey(Short eid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_EMP
     *
     * @mbggenerated Fri Jul 01 20:35:36 CST 2022
     */
    int insert(Emp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_EMP
     *
     * @mbggenerated Fri Jul 01 20:35:36 CST 2022
     */
    int insertSelective(Emp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_EMP
     *
     * @mbggenerated Fri Jul 01 20:35:36 CST 2022
     */
    List<Emp> selectByExample(EmpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_EMP
     *
     * @mbggenerated Fri Jul 01 20:35:36 CST 2022
     */
    Emp selectByPrimaryKey(Short eid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_EMP
     *
     * @mbggenerated Fri Jul 01 20:35:36 CST 2022
     */
    int updateByExampleSelective(@Param("record") Emp record, @Param("example") EmpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_EMP
     *
     * @mbggenerated Fri Jul 01 20:35:36 CST 2022
     */
    int updateByExample(@Param("record") Emp record, @Param("example") EmpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_EMP
     *
     * @mbggenerated Fri Jul 01 20:35:36 CST 2022
     */
    int updateByPrimaryKeySelective(Emp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_EMP
     *
     * @mbggenerated Fri Jul 01 20:35:36 CST 2022
     */
    int updateByPrimaryKey(Emp record);
}