package tw.com.mybatis.mapper;

import tw.com.mybatis.pojo.User;

import java.util.List;

public interface ParameterMapper {
    /**
     * 查詢所有使用者訊息
     */
    List<User> getAllUser();
}
