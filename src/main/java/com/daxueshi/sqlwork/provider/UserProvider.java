package com.daxueshi.sqlwork.provider;

import com.daxueshi.sqlwork.domain.User;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author onion
 * @date 2019-04-13 -10:11
 */
public class UserProvider {
    public String updateUser(final User user){
        return new SQL(){{
            UPDATE("users");
            if(user.getNickname()!=null){
                SET("nickname=#{nickname}");
            }
            if(user.getEmail()!=null){
                SET("email=#{email}");
            }
            if(user.getPassword()!=null){
                SET("password=#{password}");
            }
            if(user.getPhoneNumber()!=null){
                SET("phone_number=#{phoneNumber}");
            }
            if(user.getPortraitUrl()!=null){
                SET("portrait_url=#{portraitUrl}");
            }
            if(user.getRegisterTime()!=null){
                SET("register_time=#{registerTime}");
            }
            if(user.getLastEditTime()!=null){
                SET("last_edit_time=#{lastEditTime}");
            }
            WHERE("user_id=#{userId}");

        }}.toString();

    }
}
