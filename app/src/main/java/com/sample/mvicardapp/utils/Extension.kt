package com.sample.mvicardapp.utils

/***
 * Created by Nikunj Dave on 31/03/24
 *
 * Description :
 *
 * Copyright(c) 2024 mvicardapp.
 * All rights reserved.
 */
sealed interface Result<out T>

class Success<T>(val value  : T) : Result<T>
sealed interface Error : Result<Nothing> {

	/** Description of the error. */
	val message: String?
}

class ThrowableError(val throwable: Throwable) : Error {
	override val message = throwable.message
}
