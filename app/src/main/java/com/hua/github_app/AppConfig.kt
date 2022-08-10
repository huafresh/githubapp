package com.hua.github_app

import com.hua.github_app.AppConfig
import java.util.*

/**
 * Created on 2016/11/23.
 *
 * @author ThirtyDegreesRay
 */
object AppConfig {
    const val GITHUB_BASE_URL = "https://github.com/"
    const val GITHUB_API_BASE_URL = "https://api.github.com/"
    const val GITHUB_CONTENT_BASE_URL = "https://raw.githubusercontent.com/"

    /**
     * This link are for OpenHub only. Please do not use this endpoint in your applications.
     * If you want to get trending repositories, you may stand up your own instance.
     * https://github.com/thedillonb/GitHub-Trending
     */
    const val OPENHUB_BASE_URL = "http://openhub.raysus.win:3000/"
    const val HTTP_TIME_OUT = 32 * 1000
    const val HTTP_MAX_CACHE_SIZE = 32 * 1024 * 1024
    const val IMAGE_MAX_CACHE_SIZE = 32 * 1024 * 1024
    const val CACHE_MAX_AGE = 4 * 7 * 24 * 60 * 60
    const val DB_NAME = "OpenHub.db"
    const val OPENHUB_CLIENT_ID = "8f7213694e115df205fb"

    //
    const val OPENHUB_CLIENT_SECRET = "82c57672382db5c7b528d79e283c398ad02e3c3f"
    const val OAUTH2_SCOPE = "user,repo,gist,notifications"
    const val OAUTH2_URL = GITHUB_BASE_URL + "login/oauth/authorize"
    const val AUTH_HOME = GITHUB_BASE_URL + "ThirtyDegreesRay"
    const val OPENHUB_HOME = AUTH_HOME + "/OpenHub"
    const val REDIRECT_URL = "https://github.com/ThirtyDegreesRay/OpenHub/CallBack"

    //    public final static String BUGLY_APPID = BuildConfig.DEBUG ? BuildConfig.DEBUG_BUGLY_ID : BuildConfig.BUGLY_ID;
    const val OPENHUB_RELEASE_SIGN_MD5 = "C0:99:37:D9:6A:36:FB:B7:AB:4C:5E:3D:42:96:FA:AF"
    val COMMON_PAGE_URL_LIST = Arrays.asList(
        "https://github.com/trending"
    ) //    public final static boolean isCommonPageUrl(String url){
    //        if(StringUtils.isBlank(url)){
    //            return false;
    //        }
    //        for(String commonUrl : COMMON_PAGE_URL_LIST){
    //            if(url.contains(commonUrl)){
    //                return true;
    //            }
    //        }
    //        return false;
    //    }
}