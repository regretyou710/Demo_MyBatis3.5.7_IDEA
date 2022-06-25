package tw.com.mybatis.mapper;

import tw.com.mybatis.pojo.User;

import java.util.List;
import java.util.Map;

public interface ParameterMapper {
    /**
     * 添加使用者訊息
     */
    int insertUser(User user);

    /**
     * 驗證登入(參數為Map)
     */
    User checkLoginByMap(Map<String, Object> map);

    /**
     * 驗證登入
     */
    User checkLogin(String username, String password);

    /**
     * 根據使用者名稱查詢使用者訊息
     */
    User getUserByUsername(String username);

    /**
     * 查詢所有使用者訊息
     */
    List<User> getAllUser();
}
