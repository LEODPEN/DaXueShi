package com.daxueshi.sqlwork.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author onion
 * @date 2019-04-15 -14:50
 */
@Configuration
public class WeChatConfig {

    @Value("")
    private String appId;
    @Value("")
    private String appsecret;

    @Value("${wxopen.appid}")
    private String openAppId;
    @Value("${wxopen.appsecret}")
    private String openAppsecret;
    @Value("${wxopen.redirect_url")
    private String openRedirectUrl;
    /**
     * 开放平台获取access_token地址
     */
    private final static String OPEN_QRCODE_URL="";
    private final static String OPEN_ACCESS_TOKEN_URL="";
    private final static String OPEN_USER_INFO_URL="";

    public static String getOpenUserInfoUrl() {
        return OPEN_USER_INFO_URL;
    }

    public static String getOpenQrcodeUrl() {
        return OPEN_QRCODE_URL;
    }

    public static String getOpenAccessTokenUrl() {
        return OPEN_ACCESS_TOKEN_URL;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getOpenAppId() {
        return openAppId;
    }

    public void setOpenAppId(String openAppId) {
        this.openAppId = openAppId;
    }

    public String getOpenAppsecret() {
        return openAppsecret;
    }

    public void setOpenAppsecret(String openAppsecret) {
        this.openAppsecret = openAppsecret;
    }

    public String getOpenRedirectUrl() {
        return openRedirectUrl;
    }

    public void setOpenRedirectUrl(String openRedirectUrl) {
        this.openRedirectUrl = openRedirectUrl;
    }
}
