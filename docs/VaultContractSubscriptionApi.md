# VaultContractSubscriptionApi

All URIs are relative to *https://definancy.xboshy.io*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**vaultSubscribeContract**](VaultContractSubscriptionApi.md#vaultSubscribeContract) | **PUT** /api/v1/vault/{vaultId}/contract/{assetUnit}/{networkId} | Subscribe Contract to Vault |
| [**vaultUnsubscribeContract**](VaultContractSubscriptionApi.md#vaultUnsubscribeContract) | **DELETE** /api/v1/vault/{vaultId}/contract/{assetUnit}/{networkId} | Unsubscribe Contract from Vault |


<a id="vaultSubscribeContract"></a>
# **vaultSubscribeContract**
> Vault vaultSubscribeContract(vaultId, assetUnit, networkId)

Subscribe Contract to Vault

Associates a smart contract with a vault.

### Example
```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.models.*;
import com.definancy.api.VaultContractSubscriptionApi;

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

    VaultContractSubscriptionApi apiInstance = new VaultContractSubscriptionApi(apiClient);
    String vaultId = "vaultId_example"; // String | Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates.
    String assetUnit = "assetUnit_example"; // String | Ticker symbol for a digital asset (e.g., 'EUR', 'USDC', 'ETH', 'BTC'). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset.
    String networkId = "networkId_example"; // String | Unique identifier for a specific blockchain network (e.g., 'ethereum', 'algorand'). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network.
    try {
      Vault result = apiInstance.vaultSubscribeContract(vaultId, assetUnit, networkId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling VaultContractSubscriptionApi#vaultSubscribeContract");
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
| **vaultId** | **String**| Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. | |
| **assetUnit** | **String**| Ticker symbol for a digital asset (e.g., &#39;EUR&#39;, &#39;USDC&#39;, &#39;ETH&#39;, &#39;BTC&#39;). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset. | |
| **networkId** | **String**| Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. | |

### Return type

[**Vault**](Vault.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Contract successfully subscribed to vault. |  * Cache-Control -  <br>  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |

<a id="vaultUnsubscribeContract"></a>
# **vaultUnsubscribeContract**
> Vault vaultUnsubscribeContract(vaultId, assetUnit, networkId)

Unsubscribe Contract from Vault

Removes association between a contract and vault.

### Example
```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.models.*;
import com.definancy.api.VaultContractSubscriptionApi;

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

    VaultContractSubscriptionApi apiInstance = new VaultContractSubscriptionApi(apiClient);
    String vaultId = "vaultId_example"; // String | Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates.
    String assetUnit = "assetUnit_example"; // String | Ticker symbol for a digital asset (e.g., 'EUR', 'USDC', 'ETH', 'BTC'). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset.
    String networkId = "networkId_example"; // String | Unique identifier for a specific blockchain network (e.g., 'ethereum', 'algorand'). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network.
    try {
      Vault result = apiInstance.vaultUnsubscribeContract(vaultId, assetUnit, networkId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling VaultContractSubscriptionApi#vaultUnsubscribeContract");
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
| **vaultId** | **String**| Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. | |
| **assetUnit** | **String**| Ticker symbol for a digital asset (e.g., &#39;EUR&#39;, &#39;USDC&#39;, &#39;ETH&#39;, &#39;BTC&#39;). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset. | |
| **networkId** | **String**| Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. | |

### Return type

[**Vault**](Vault.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Contract successfully unsubscribed from vault. |  * Cache-Control -  <br>  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |

