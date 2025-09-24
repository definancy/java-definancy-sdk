# ProbeApi

All URIs are relative to *https://definancy.xboshy.io*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**healthyCheck**](ProbeApi.md#healthyCheck) | **GET** /api/v1/healthy | Health Check |
| [**readyCheck**](ProbeApi.md#readyCheck) | **GET** /api/v1/ready | Ready Check |


<a id="healthyCheck"></a>
# **healthyCheck**
> Status healthyCheck()

Health Check

Verifies service liveness. Returns OK when operational. This endpoint is used to check if the service is alive and ready to handle requests,  ensuring that your applications can rely on the Definancy service for their operations.

### Example
```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.models.*;
import com.definancy.api.ProbeApi;

public class Example {
  public static void main(String[] args) {
    String network = "dev";
    String audience = "https://definancy.xboshy.io";

    LocalAttestor localAttestor = new LocalAttestor(network, audience);
    AuthInterceptor authInterceptor = new AuthInterceptor(localAttestor);

    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient httpClient = new OkHttpClient.Builder()
      .addInterceptor(authInterceptor)
      .addInterceptor(loggingInterceptor) // Optional: for debugging
      .connectTimeout(30, TimeUnit.SECONDS)
      .readTimeout(30, TimeUnit.SECONDS)
      .build();

    ApiClient apiClient = new ApiClient();
    apiClient.setHttpClient(httpClient);

    ProbeApi apiInstance = new ProbeApi(apiClient);
    try {
      Status result = apiInstance.healthyCheck();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProbeApi#healthyCheck");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**Status**](Status.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK. |  * Cache-Control -  <br>  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |

<a id="readyCheck"></a>
# **readyCheck**
> Status readyCheck()

Ready Check

Verifies service readiness. Returns OK when ready to accept traffic. This endpoint checks if the service is ready to handle incoming requests, ensuring that your applications can rely on the Definancy service for their operations.

### Example
```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.models.*;
import com.definancy.api.ProbeApi;

public class Example {
  public static void main(String[] args) {
    String network = "dev";
    String audience = "https://definancy.xboshy.io";

    LocalAttestor localAttestor = new LocalAttestor(network, audience);
    AuthInterceptor authInterceptor = new AuthInterceptor(localAttestor);

    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient httpClient = new OkHttpClient.Builder()
      .addInterceptor(authInterceptor)
      .addInterceptor(loggingInterceptor) // Optional: for debugging
      .connectTimeout(30, TimeUnit.SECONDS)
      .readTimeout(30, TimeUnit.SECONDS)
      .build();

    ApiClient apiClient = new ApiClient();
    apiClient.setHttpClient(httpClient);

    ProbeApi apiInstance = new ProbeApi(apiClient);
    try {
      Status result = apiInstance.readyCheck();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProbeApi#readyCheck");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**Status**](Status.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK. |  * Cache-Control -  <br>  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |

