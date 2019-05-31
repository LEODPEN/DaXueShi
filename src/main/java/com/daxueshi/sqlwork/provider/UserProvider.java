package com.daxueshi.sqlwork.provider;

import com.daxueshi.sqlwork.domain.User;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author onion
 * @date 2019-05-31 -11:33
 */
public class UserProvider {
    public String updateUser(final User user){
        return new SQL(){{
            UPDATE("users");
            if(user.getPassword() != null){
                SET("password = #{password}");
            }
            if(user.getNickname() != null){
                SET("nickname = #{nickname}");
            }
            if(user.getProfile() != null){
                SET("profile = #{profile}");
            }
            if(user.getLastLoginTime() != null){
                SET("last_login_time = #{lastLoginTime");
            }
            if (user.getStatus() != null){
                SET("status = #{status}");
            }
            WHERE("email = #{email}");
        }}.toString();
    }
}
