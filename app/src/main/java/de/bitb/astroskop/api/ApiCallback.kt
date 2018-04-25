package de.bitb.astroskop.api

import java.net.HttpURLConnection

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class ApiCallback<T> : Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        val statusCode = response.code()

        if (statusCode in STATUS_SUCCESS..(STATUS_REDIRECT - 1)) {
            processSuccess(call, response, statusCode)
        } else if (statusCode in STATUS_REDIRECT..(STATUS_CLIENT_ERROR - 1)) {
            processRedirect(call, response, statusCode)
        } else if (statusCode in STATUS_CLIENT_ERROR..(STATUS_SERVER_ERROR - 1)) {
            processClientError(call, response, statusCode)
        } else if (statusCode in STATUS_SERVER_ERROR..(STATUS_UNKNOWN - 1)) {
            processServerError(call, response, statusCode)
        } else {
            onUnknownStatusCode(call, response)
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable?) {
        onError(call, t as Any)
    }

    private fun processSuccess(call: Call<T>, response: Response<T>, statusCode: Int?) {
        when (statusCode) {
            HttpURLConnection.HTTP_OK -> onSuccess(call, response.body())
            HttpURLConnection.HTTP_CREATED -> onCreated(call, response.body())
            HttpURLConnection.HTTP_ACCEPTED -> onAccepted(call, response.body())
            else -> onUnknownSuccess(call, response)
        }
    }

    private fun processRedirect(call: Call<T>, response: Response<T>, statusCode: Int?) {
        when (statusCode) {
            else -> onRedirect(call, response)
        }
    }

    private fun processClientError(call: Call<T>, response: Response<T>, statusCode: Int?) {
        onError(call, response)
        onClientError(call, response)

        when (statusCode) {
            HttpURLConnection.HTTP_BAD_REQUEST -> onBadRequest(call, response)
            HttpURLConnection.HTTP_UNAUTHORIZED -> onUnauthorized(call, response)
            HttpURLConnection.HTTP_FORBIDDEN -> onForbidden(call, response)
            HttpURLConnection.HTTP_NOT_FOUND -> onNotFound(call, response)
            else -> onUnknownClientError(call, response)
        }
    }

    private fun processServerError(call: Call<T>, response: Response<T>, statusCode: Int?) {
        onError(call, response)
        onServerError(call, response)

        when (statusCode) {
            HttpURLConnection.HTTP_INTERNAL_ERROR -> onInternalError(call, response)
            HttpURLConnection.HTTP_BAD_GATEWAY -> onBadGateway(call, response)
            HttpURLConnection.HTTP_UNAVAILABLE -> onUnavailable(call, response)
            HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> onGatewayTimeout(call, response)
            else -> onUnknownServerError(call, response)
        }
    }

    // # # # # # SUCCESS

    /**
     * Http-Status 200
     */
    fun onSuccess(call: Call<*>, response: T?) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 202
     */
    fun onAccepted(call: Call<T>, body: T?) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 201
     */
    fun onCreated(call: Call<T>, body: T?) {
        // empty method, available for implementation
    }

    /**
     * Http-Status in range of 203 to 299
     */
    fun onUnknownSuccess(call: Call<T>, response: Response<T>) {
        // empty method, available for implementation
    }

    // # # # # # REDIRECT

    /**
     * Http-Status in range of 300 to 399
     */
    fun onRedirect(call: Call<T>, response: Response<T>) {
        // empty method, available for implementation
    }

    // # # # # # CLIENT ERROR

    /**
     * Http-Status in range of 400 to 499
     */
    fun onClientError(call: Call<T>, response: Response<T>) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 402 and in range from 405 to 499
     */
    fun onUnknownClientError(call: Call<T>, response: Response<T>) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 404
     */
    fun onNotFound(call: Call<T>, response: Response<T>) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 403
     */
    fun onForbidden(call: Call<T>, response: Response<T>) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 401
     */
    fun onUnauthorized(call: Call<T>, response: Response<T>) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 400
     */
    fun onBadRequest(call: Call<T>, response: Response<T>) {
        // empty method, available for implementation
    }

    // # # # # # SERVER ERROR

    /**
     * Http-Status 500 to 599
     */
    fun onServerError(call: Call<T>, response: Response<T>) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 500
     */
    fun onInternalError(call: Call<T>, response: Response<T>) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 502
     */
    fun onBadGateway(call: Call<T>, response: Response<T>) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 503
     */
    fun onUnavailable(call: Call<T>, response: Response<T>) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 504
     */
    fun onGatewayTimeout(call: Call<T>, response: Response<T>) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 501 and in range of 505 to 599
     */
    fun onUnknownServerError(call: Call<T>, response: Response<T>) {
        // empty method, available for implementation
    }

    // # # # # # OTHER

    /**
     * Http-Status from 0 to 199 and 600 to x
     */
    fun onUnknownStatusCode(call: Call<*>, response: Response<T>) {
        // empty method, available for implementation
    }

    // # # # # # ERROR

    /**
     * Called in every Error Case
     */
    fun onError(call: Call<*>, response: Any) {
        // empty method, available for implementation
    }

    companion object {

        private val STATUS_SUCCESS = 200
        private val STATUS_REDIRECT = 300
        private val STATUS_CLIENT_ERROR = 400
        private val STATUS_SERVER_ERROR = 500
        private val STATUS_UNKNOWN = 600
    }

}
