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
    const val OPENHUB_CLIENT_ID = "8f7213694e115df205fb"
    const val OPENHUB_CLIENT_SECRET = "82c57672382db5c7b528d79e283c398ad02e3c3f"
    const val OAUTH2_SCOPE = "user,repo,gist,notifications"
    const val OAUTH2_URL = GITHUB_BASE_URL + "login/oauth/authorize"

}