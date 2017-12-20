package de.bitb.astroskop.api;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ApiCallback<T> implements Callback<T> {

    private static final int STATUS_SUCCESS = 200;
    private static final int STATUS_REDIRECT = 300;
    private static final int STATUS_CLIENT_ERROR = 400;
    private static final int STATUS_SERVER_ERROR = 500;
    private static final int STATUS_UNKNOWN = 600;

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        Integer statusCode = response.code();

        if (statusCode >= STATUS_SUCCESS && statusCode < STATUS_REDIRECT) {
            processSuccess(call, response, statusCode);
        } else if (statusCode >= STATUS_REDIRECT && statusCode < STATUS_CLIENT_ERROR) {
            processRedirect(call, response, statusCode);
        } else if (statusCode >= STATUS_CLIENT_ERROR && statusCode < STATUS_SERVER_ERROR) {
            processClientError(call, response, statusCode);
        } else if (statusCode >= STATUS_SERVER_ERROR && statusCode < STATUS_UNKNOWN) {
            processServerError(call, response, statusCode);
        } else {
            onUnknownStatusCode(call, response);
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        onError(call, t);
    }

    private void processSuccess(Call<T> call, Response<T> response, Integer statusCode) {
        switch (statusCode) {
            case HttpURLConnection.HTTP_OK:
                onSuccess(call, response.body());
                break;
            case HttpURLConnection.HTTP_CREATED:
                onCreated(call, response.body());
                break;
            case HttpURLConnection.HTTP_ACCEPTED:
                onAccepted(call, response.body());
                break;
            default:
                onUnknownSuccess(call, response);
        }
    }

    private void processRedirect(Call<T> call, Response<T> response, Integer statusCode) {
        switch (statusCode) {
            default:
                onRedirect(call, response);
        }
    }

    private void processClientError(Call<T> call, Response<T> response, Integer statusCode) {
        onError(call, response);
        onClientError(call, response);

        switch (statusCode) {
            case HttpURLConnection.HTTP_BAD_REQUEST:
                onBadRequest(call, response);
                break;
            case HttpURLConnection.HTTP_UNAUTHORIZED:
                onUnauthorized(call, response);
                break;
            case HttpURLConnection.HTTP_FORBIDDEN:
                onForbidden(call, response);
                break;
            case HttpURLConnection.HTTP_NOT_FOUND:
                onNotFound(call, response);
                break;
            default:
                onUnknownClientError(call, response);
        }
    }

    private void processServerError(Call<T> call, Response<T> response, Integer statusCode) {
        onError(call, response);
        onServerError(call, response);

        switch (statusCode) {
            case HttpURLConnection.HTTP_INTERNAL_ERROR:
                onInternalError(call, response);
                break;
            case HttpURLConnection.HTTP_BAD_GATEWAY:
                onBadGateway(call, response);
                break;
            case HttpURLConnection.HTTP_UNAVAILABLE:
                onUnavailable(call, response);
                break;
            case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
                onGatewayTimeout(call, response);
                break;
            default:
                onUnknownServerError(call, response);
        }
    }

    // # # # # # SUCCESS

    /**
     * Http-Status 200
     */
    public void onSuccess(Call call, T response) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 202
     */
    public void onAccepted(Call<T> call, T body) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 201
     */
    public void onCreated(Call<T> call, T body) {
        // empty method, available for implementation
    }

    /**
     * Http-Status in range of 203 to 299
     */
    public void onUnknownSuccess(Call<T> call, Response<T> response) {
        // empty method, available for implementation
    }

    // # # # # # REDIRECT

    /**
     * Http-Status in range of 300 to 399
     */
    public void onRedirect(Call<T> call, Response<T> response) {
        // empty method, available for implementation
    }

    // # # # # # CLIENT ERROR

    /**
     * Http-Status in range of 400 to 499
     */
    public void onClientError(Call<T> call, Response<T> response) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 402 and in range from 405 to 499
     */
    public void onUnknownClientError(Call<T> call, Response<T> response) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 404
     */
    public void onNotFound(Call<T> call, Response<T> response) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 403
     */
    public void onForbidden(Call<T> call, Response<T> response) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 401
     */
    public void onUnauthorized(Call<T> call, Response<T> response) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 400
     */
    public void onBadRequest(Call<T> call, Response<T> response) {
        // empty method, available for implementation
    }

    // # # # # # SERVER ERROR

    /**
     * Http-Status 500 to 599
     */
    public void onServerError(Call<T> call, Response<T> response) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 500
     */
    public void onInternalError(Call<T> call, Response<T> response) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 502
     */
    public void onBadGateway(Call<T> call, Response<T> response) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 503
     */
    public void onUnavailable(Call<T> call, Response<T> response) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 504
     */
    public void onGatewayTimeout(Call<T> call, Response<T> response) {
        // empty method, available for implementation
    }

    /**
     * Http-Status 501 and in range of 505 to 599
     */
    public void onUnknownServerError(Call<T> call, Response<T> response) {
        // empty method, available for implementation
    }

    // # # # # # OTHER

    /**
     * Http-Status from 0 to 199 and 600 to x
     */
    public void onUnknownStatusCode(Call call, Response<T> response) {
        // empty method, available for implementation
    }

    // # # # # # ERROR

    /**
     * Called in every Error Case
     */
    public void onError(Call call, Object response) {
        // empty method, available for implementation
    }

}
