package tw.com.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import tw.com.mybatis.pojo.User;

import java.util.List;
import java.util.Map;

public interface SelectMapper {
    /**
     * 根據id查詢使用者訊息
     */
    //User getUserById(@Param("id") Integer id);
    List<User> getUserById(@Param("id") Integer id);

    /**
     * 查詢所有使用者訊息
     */
    List<User> getAllUser();

    /**
     * 查詢使用者的總記錄數
     */
    Integer getCount();

    /**
     * 根據id查詢用戶訊息為一個map集合
     */
    Map<String, Object> getUserByIdToMap(@Param("id") Integer id);
}
