package com.example.carrot.model

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class SignUpRequest(
    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String
)

data class SignUpResponse(
    val email: String
)

data class LogInResponse(
    val access_token: String,
    val token_type: String,
    val refresh_token: String
)

data class EmailVerifResponse(
    val status: String,
    val detail: String
)

data class ChangeNicknameRequest(
    @SerializedName("new_nickname")
    val new_nickname: String
)

data class GetMyInfoResponse(
    val email: String,
    val nickname: String,
    @SerializedName("manner_temporature")
    val manner_temp: Float,
    val create_at: String,
    val locate: String
)

class TokenStore(
    private val context: Context,
) {
    companion object {
        private val Context.datastore: DataStore<Preferences> by preferencesDataStore("token")
        val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    }


    val getAccessToken: Flow<String> = context.datastore.data
        .map { preference ->
            preference[ACCESS_TOKEN_KEY] ?: ""
        }

    suspend fun saveAccessToken(access_token: String){
        context.datastore.edit { preference ->
            preference[ACCESS_TOKEN_KEY] = access_token
            Log.i("LOGIN", "saved access_token : $access_token")
        }
    }

    val getRefreshToken: Flow<String?> = context.datastore.data
        .map { preference ->
            preference[REFRESH_TOKEN_KEY] ?: ""
        }

    suspend fun saveRefreshToken(refresh_token: String){
        context.datastore.edit { preference ->
            preference[REFRESH_TOKEN_KEY] = refresh_token
            Log.i("LOGIN", "saved refresh_token : $refresh_token")
        }
    }
}