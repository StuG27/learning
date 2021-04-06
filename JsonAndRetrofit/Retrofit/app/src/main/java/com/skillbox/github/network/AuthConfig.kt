package com.skillbox.github.network

import net.openid.appauth.ResponseTypeValues


object AuthConfig {

    const val AUTH_URI = "https://github.com/login/oauth/authorize"
    const val TOKEN_URI = "https://github.com/login/oauth/access_token"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "user,repo"

    const val CLIENT_ID = "53dce5eb01d694d33867"
    const val CLIENT_SECRET = "e1a033ea4a164a234785e8b312e5b4e872a0d627"
    const val CALLBACK_URL = "stug27://stug27.ru/callback"
}