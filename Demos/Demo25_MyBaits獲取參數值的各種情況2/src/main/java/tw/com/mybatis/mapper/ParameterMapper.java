package tw.com.mybatis.mapper;

import tw.com.mybatis.pojo.User;

import java.util.List;

public interface ParameterMapper {
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
