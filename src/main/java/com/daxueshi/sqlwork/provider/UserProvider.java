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
            if(user.getPassword()!=null) {
                SET("password=#{password}");
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
            WHERE("email=#{email}");

        }}.toString();
    }
    public String insertUser(final User user){
        return new SQL(){{
            INSERT_INTO("users");
            if(user.getEmail()!= null){
                VALUES("email",user.getEmail());
            }
            if(user.getNickname()!=null){
                VALUES("nickname",user.getNickname());
            }
            if(user.getPassword()!=null){
                VALUES("password",user.getPassword());
            }
            if(user.getPortraitUrl()!=null){
                VALUES("portrait_url",user.getPortraitUrl());
            }
            if(user.getStatus()!=null){
                VALUES("status",user.getStatus().toString());
            }
            if (user.getRegisterTime()!=null){
                VALUES("register_time",user.getRegisterTime().toString());
            }
            if(user.getLastEditTime()!=null){
                VALUES("last_edit_time",user.getLastEditTime().toString());
            }

        }}.toString();
    }
}
