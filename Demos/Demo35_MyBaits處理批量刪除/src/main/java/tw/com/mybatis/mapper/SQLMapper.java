package tw.com.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import tw.com.mybatis.pojo.User;

import java.util.List;
import java.util.Map;

public interface SQLMapper {
    /**
     * 根據使用者名稱模糊查詢訊息
     */
    List<User> getUserByLike(@Param("username") String username);

    /**
     * 批量刪除
     */
    int deleteMore(@Param("ids") String ids);
}
