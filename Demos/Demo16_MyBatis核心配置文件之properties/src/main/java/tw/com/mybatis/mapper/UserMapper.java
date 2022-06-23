package tw.com.mybatis.mapper;

import tw.com.mybatis.pojo.User;

import java.util.List;

public interface UserMapper {
    /**
     * MyBatis面向介面編程的兩個一致:
     * 1. 映射文件的namespace要和mapper介面的全類名保持一致
     * 2. 映射文件中SQL語句的id要和mapper介面中的方法名一致
     *
     * 表--實體類--mapper介面--映射文件
     */

    /**
     * 添加用戶訊息
     */
    int insertUser();

    /**
     * 修改用戶訊息
     */
    void updateUser();

    /**
     * 刪除用戶訊息
     */
    void deleteUser();

    /**
     * 根據用戶id查詢訊息
     */
    User getUserById();

    /**
     * 查詢所有用戶訊息
     */
    List<User> getAllUser();
}
