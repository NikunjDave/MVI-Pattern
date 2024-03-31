package com.sample.mvicardapp.data.remote

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException
import javax.inject.Inject

/**
 * Return mock response
 */
class ApiInterceptor @Inject constructor(private val context: Context) : Interceptor {
	override fun intercept(chain: Interceptor.Chain): Response {
		if (chain.request().url.toUri().toString().endsWith("login")) {
			val responseString = loadJSONFromAsset(context, "login.json")
			return chain.proceed(chain.request())
				.newBuilder()
				.code(200)
				.protocol(Protocol.HTTP_2)
				.message(responseString.orEmpty())
				.body(
					responseString
						?.toByteArray()
						?.toResponseBody(
							"application/json".toMediaTypeOrNull()
						)
				)
				.addHeader("content-type", "application/json")
				.build()
		} else {
			//Skip the interception.
			return chain.proceed(chain.request())
		}
	}

	private fun loadJSONFromAsset(context: Context, filename: String): String? {
		var json: String? = null
		try {
			val inputStream = context.assets.open(filename)
			val size = inputStream.available()
			val buffer = ByteArray(size)
			inputStream.read(buffer)
			inputStream.close()
			json = String(buffer, charset("UTF-8"))
		} catch (e: IOException) {
			e.printStackTrace()
		}
		return json
	}

}

