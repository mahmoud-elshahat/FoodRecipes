package com.example.foodrecipes.presentation.utils

import android.content.Context
import com.example.foodrecipes.R
import java.util.*

object HttpStatusCode {
    private const val API_LIMIT_EXCEEDED = "402"
    private const val RESULT_NOT_FOUND = "404"

    fun getMeaningfulMessage(httpStatusCode: String, context: Context): String {
        return when (httpStatusCode) {
            API_LIMIT_EXCEEDED ->
                context.getString(R.string.error_http_api_limit)
            RESULT_NOT_FOUND ->
                context.getString(R.string.error_http_not_found)
            else ->
                context.getString(R.string.error_network_error)
        }
    }

    fun getHttpCodeFromLoadError(error: String): String {
        return error
            .uppercase(Locale.getDefault())
            .replace("HTTP", "")
            .replace(" ", "")
    }
}


