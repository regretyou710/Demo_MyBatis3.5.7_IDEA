package tw.com.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import tw.com.mybatis.pojo.User;

import java.util.List;

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
}
