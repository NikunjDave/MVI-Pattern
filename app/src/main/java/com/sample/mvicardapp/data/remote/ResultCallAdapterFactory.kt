package com.sample.mvicardapp.data.remote

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import com.sample.mvicardapp.utils.Result
import com.sample.mvicardapp.utils.Success
import com.sample.mvicardapp.utils.ThrowableError

/***
 * Created by Nikunj Dave on 31/03/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */
class ResultCallAdapterFactory : CallAdapter.Factory() {
	override fun get(
		returnType: Type,
		annotations: Array<out Annotation>,
		retrofit: Retrofit
	): CallAdapter<*, *>? {

		if(getRawType(returnType) != Call::class.java)
			return null
		if(returnType !is ParameterizedType)
			throw IllegalStateException("return type must be parameterized")

		val responseType = getParameterUpperBound(0, returnType)

		if (getRawType(responseType) != Result::class.java)
			return null // return type of interface are not Result Type

		if (responseType !is ParameterizedType)
			throw IllegalStateException("$responseType must be parameterized.")

		val successType = getParameterUpperBound(0, responseType)
		/*val errorConverter = retrofit.nextResponseBodyConverter<ServerError>(
			null,
			ServerError::class.java,
			annotations
		)*/
		return ResultCallAdapter<Any>(successType)


	}
}

class ResultCallAdapter<R> (private val responseType : Type): CallAdapter<R, Call<Result<R>>> {
	override fun responseType(): Type {
		return responseType
	}

	override fun adapt(call: Call<R>): Call<Result<R>> {
		return ResultCall(call)
	}

}


class ResultCall<R>(private val delegate: Call<R>) : Call<Result<R>> {

	override fun enqueue(callback: Callback<Result<R>>) {
		delegate.enqueue(object  : Callback<R> {
			override fun onResponse(call: Call<R>, response: Response<R>) {
				if(response.isSuccessful){
					val body = response.body()
					if(body != null){
						callback.onResponse(this@ResultCall, Response.success(Success(body)))
					}else{
						callback.onResponse(
							this@ResultCall,
							Response.success(
								ThrowableError(RuntimeException("Response body is empty"))
							)
						)
					}
				}

			}

			override fun onFailure(call: Call<R>, t: Throwable) {
				callback.onResponse(
					this@ResultCall,
					Response.success(ThrowableError(t))
				)
			}

		})

	}

	override fun clone(): Call<Result<R>> =  ResultCall(delegate.clone())


	override fun execute(): Response<Result<R>> {
		throw UnsupportedOperationException("Synchronous operation is not supported in ResultCall")
	}

	override fun isExecuted(): Boolean = delegate.isExecuted


	override fun cancel() = delegate.cancel()

	override fun isCanceled(): Boolean = delegate.isCanceled

	override fun request(): Request = delegate.request()

	override fun timeout(): Timeout = delegate.timeout()


}