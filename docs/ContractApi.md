# ContractApi

All URIs are relative to *https://definancy.xboshy.io*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**configContract**](ContractApi.md#configContract) | **PATCH** /api/v1/contract/{assetUnit}/{networkId} | Configure Contract |
| [**getContract**](ContractApi.md#getContract) | **GET** /api/v1/contract/{assetUnit}/{networkId} | Get Contract |
| [**getContracts**](ContractApi.md#getContracts) | **GET** /api/v1/contract | List Contracts |


<a id="configContract"></a>
# **configContract**
> Contract configContract(assetUnit, networkId, contractConfig)

Configure Contract

Updates configuration parameters for a contract. This endpoint allows you to update configuration parameters for a specific contract, which is essential for executing and validating payments on networks.

### Example
```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.models.*;
import com.definancy.api.ContractApi;

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

    ContractApi apiInstance = new ContractApi(apiClient);
    String assetUnit = "assetUnit_example"; // String | Ticker symbol for a digital asset (e.g., 'EUR', 'USDC', 'ETH', 'BTC'). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset.
    String networkId = "networkId_example"; // String | Unique identifier for a specific blockchain network (e.g., 'ethereum', 'algorand'). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network.
    ContractConfig contractConfig = new ContractConfig(); // ContractConfig | Contract configuration parameters to update. Currently supports only the 'enabled'  field for controlling contract availability. When setting 'enabled' to false, the  contract will be disabled for payment processing while preserving all existing  vault associations, payment acceptance, and historical transaction data.
    try {
      Contract result = apiInstance.configContract(assetUnit, networkId, contractConfig);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ContractApi#configContract");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **assetUnit** | **String**| Ticker symbol for a digital asset (e.g., &#39;EUR&#39;, &#39;USDC&#39;, &#39;ETH&#39;, &#39;BTC&#39;). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset. | |
| **networkId** | **String**| Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. | |
| **contractConfig** | [**ContractConfig**](ContractConfig.md)| Contract configuration parameters to update. Currently supports only the &#39;enabled&#39;  field for controlling contract availability. When setting &#39;enabled&#39; to false, the  contract will be disabled for payment processing while preserving all existing  vault associations, payment acceptance, and historical transaction data. | |

### Return type

[**Contract**](Contract.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Contract configuration updated successfully. |  * Cache-Control -  <br>  |
| **400** | The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. |  -  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |

<a id="getContract"></a>
# **getContract**
> Contract getContract(assetUnit, networkId)

Get Contract

Retrieves configuration details for a specific smart contract. This endpoint returns detailed configuration information for a specific contract, which is essential for executing and validating payments on networks.

### Example
```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.models.*;
import com.definancy.api.ContractApi;

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

    ContractApi apiInstance = new ContractApi(apiClient);
    String assetUnit = "assetUnit_example"; // String | Ticker symbol for a digital asset (e.g., 'EUR', 'USDC', 'ETH', 'BTC'). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset.
    String networkId = "networkId_example"; // String | Unique identifier for a specific blockchain network (e.g., 'ethereum', 'algorand'). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network.
    try {
      Contract result = apiInstance.getContract(assetUnit, networkId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ContractApi#getContract");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **assetUnit** | **String**| Ticker symbol for a digital asset (e.g., &#39;EUR&#39;, &#39;USDC&#39;, &#39;ETH&#39;, &#39;BTC&#39;). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset. | |
| **networkId** | **String**| Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. | |

### Return type

[**Contract**](Contract.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Contract information retrieved successfully. |  * Cache-Control -  <br>  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |

<a id="getContracts"></a>
# **getContracts**
> List&lt;Contract&gt; getContracts()

List Contracts

Retrieves all contract configurations. This endpoint returns a list of all configured contracts, which are essential for executing and validating payments on networks.

### Example
```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.models.*;
import com.definancy.api.ContractApi;

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

    ContractApi apiInstance = new ContractApi(apiClient);
    try {
      List<Contract> result = apiInstance.getContracts();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ContractApi#getContracts");
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

[**List&lt;Contract&gt;**](Contract.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Contract list retrieved successfully. |  * Cache-Control -  <br>  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |

