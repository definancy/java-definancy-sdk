# VaultPaymentApi

All URIs are relative to *https://definancy.xboshy.io*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**archivePaymentAcceptance**](VaultPaymentApi.md#archivePaymentAcceptance) | **DELETE** /api/v1/vault/{vaultId}/payment/acceptance/{paymentAcceptanceId} | Archive Payment Acceptance |
| [**createPaymentAcceptance**](VaultPaymentApi.md#createPaymentAcceptance) | **PUT** /api/v1/vault/{vaultId}/payment/acceptance | Create Payment Acceptance |
| [**getPaymentAcceptance**](VaultPaymentApi.md#getPaymentAcceptance) | **GET** /api/v1/vault/{vaultId}/payment/acceptance/{paymentAcceptanceId} | Get Payment Acceptance |
| [**linkPaymentAcceptanceDocument**](VaultPaymentApi.md#linkPaymentAcceptanceDocument) | **PUT** /api/v1/vault/{vaultId}/payment/acceptance/{paymentAcceptanceId}/document/{documentId} | Link Document to Payment Acceptance |
| [**unlinkPaymentAcceptanceDocument**](VaultPaymentApi.md#unlinkPaymentAcceptanceDocument) | **DELETE** /api/v1/vault/{vaultId}/payment/acceptance/{paymentAcceptanceId}/document/{documentId} | Unlink Document from Payment Acceptance |
| [**updatePaymentAcceptance**](VaultPaymentApi.md#updatePaymentAcceptance) | **PATCH** /api/v1/vault/{vaultId}/payment/acceptance/{paymentAcceptanceId} | Update Payment Acceptance |
| [**vaultGetPaymentEstimate**](VaultPaymentApi.md#vaultGetPaymentEstimate) | **POST** /api/v1/vault/{vaultId}/payment/estimate | Generate Payment Estimate |


<a id="archivePaymentAcceptance"></a>
# **archivePaymentAcceptance**
> PaymentAcceptance archivePaymentAcceptance(vaultId, paymentAcceptanceId)

Archive Payment Acceptance

Deactivates a payment acceptance while preserving historical records.

### Example
```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.models.*;
import com.definancy.api.VaultPaymentApi;

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

    VaultPaymentApi apiInstance = new VaultPaymentApi(apiClient);
    String vaultId = "vaultId_example"; // String | Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates.
    UUID paymentAcceptanceId = UUID.randomUUID(); // UUID | Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information.
    try {
      PaymentAcceptance result = apiInstance.archivePaymentAcceptance(vaultId, paymentAcceptanceId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling VaultPaymentApi#archivePaymentAcceptance");
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
| **paymentAcceptanceId** | **UUID**| Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information. | |

### Return type

[**PaymentAcceptance**](PaymentAcceptance.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Payment acceptance archived successfully. |  * Cache-Control -  <br>  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |

<a id="createPaymentAcceptance"></a>
# **createPaymentAcceptance**
> PaymentAcceptance createPaymentAcceptance(vaultId, paymentAcceptanceConfig)

Create Payment Acceptance

Initiates a new payment acceptance process.

### Example
```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.models.*;
import com.definancy.api.VaultPaymentApi;

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

    VaultPaymentApi apiInstance = new VaultPaymentApi(apiClient);
    String vaultId = "vaultId_example"; // String | Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates.
    PaymentAcceptanceConfig paymentAcceptanceConfig = new PaymentAcceptanceConfig(); // PaymentAcceptanceConfig | Payment acceptance configuration including required payment scenarios and optional order context. The 'price-scenarios' list must contain at least one contract-amount pair and all contracts must be subscribed to the vault. Optional 'order' provides commercial context, and 'documents' can reference existing compliance documents.
    try {
      PaymentAcceptance result = apiInstance.createPaymentAcceptance(vaultId, paymentAcceptanceConfig);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling VaultPaymentApi#createPaymentAcceptance");
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
| **paymentAcceptanceConfig** | [**PaymentAcceptanceConfig**](PaymentAcceptanceConfig.md)| Payment acceptance configuration including required payment scenarios and optional order context. The &#39;price-scenarios&#39; list must contain at least one contract-amount pair and all contracts must be subscribed to the vault. Optional &#39;order&#39; provides commercial context, and &#39;documents&#39; can reference existing compliance documents. | |

### Return type

[**PaymentAcceptance**](PaymentAcceptance.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Payment acceptance created successfully. |  * Location - URL of the submitted document. <br>  * Cache-Control -  <br>  |
| **400** | The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. |  -  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |

<a id="getPaymentAcceptance"></a>
# **getPaymentAcceptance**
> PaymentAcceptance getPaymentAcceptance(vaultId, paymentAcceptanceId)

Get Payment Acceptance

Retrieves details of an existing payment acceptance.

### Example
```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.models.*;
import com.definancy.api.VaultPaymentApi;

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

    VaultPaymentApi apiInstance = new VaultPaymentApi(apiClient);
    String vaultId = "vaultId_example"; // String | Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates.
    UUID paymentAcceptanceId = UUID.randomUUID(); // UUID | Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information.
    try {
      PaymentAcceptance result = apiInstance.getPaymentAcceptance(vaultId, paymentAcceptanceId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling VaultPaymentApi#getPaymentAcceptance");
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
| **paymentAcceptanceId** | **UUID**| Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information. | |

### Return type

[**PaymentAcceptance**](PaymentAcceptance.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Payment acceptance details retrieved. |  * Cache-Control -  <br>  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |

<a id="linkPaymentAcceptanceDocument"></a>
# **linkPaymentAcceptanceDocument**
> PaymentAcceptance linkPaymentAcceptanceDocument(vaultId, paymentAcceptanceId, documentId)

Link Document to Payment Acceptance

Associates a document with a payment acceptance. Supported document types: personal identification, entity documentation,  wallet verification data.

### Example
```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.models.*;
import com.definancy.api.VaultPaymentApi;

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

    VaultPaymentApi apiInstance = new VaultPaymentApi(apiClient);
    String vaultId = "vaultId_example"; // String | Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates.
    UUID paymentAcceptanceId = UUID.randomUUID(); // UUID | Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information.
    UUID documentId = UUID.randomUUID(); // UUID | Unique identifier for a compliance document within a vault. Used for document retrieval, status checking, linking to payment acceptance, and managing the document verification lifecycle.
    try {
      PaymentAcceptance result = apiInstance.linkPaymentAcceptanceDocument(vaultId, paymentAcceptanceId, documentId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling VaultPaymentApi#linkPaymentAcceptanceDocument");
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
| **paymentAcceptanceId** | **UUID**| Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information. | |
| **documentId** | **UUID**| Unique identifier for a compliance document within a vault. Used for document retrieval, status checking, linking to payment acceptance, and managing the document verification lifecycle. | |

### Return type

[**PaymentAcceptance**](PaymentAcceptance.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Document successfully linked to payment acceptance. |  * Cache-Control -  <br>  |
| **400** | The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. |  -  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |

<a id="unlinkPaymentAcceptanceDocument"></a>
# **unlinkPaymentAcceptanceDocument**
> PaymentAcceptance unlinkPaymentAcceptanceDocument(vaultId, paymentAcceptanceId, documentId)

Unlink Document from Payment Acceptance

Removes document association from active consideration in payment acceptance while retaining it for audit purposes.

### Example
```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.models.*;
import com.definancy.api.VaultPaymentApi;

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

    VaultPaymentApi apiInstance = new VaultPaymentApi(apiClient);
    String vaultId = "vaultId_example"; // String | Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates.
    UUID paymentAcceptanceId = UUID.randomUUID(); // UUID | Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information.
    UUID documentId = UUID.randomUUID(); // UUID | Unique identifier for a compliance document within a vault. Used for document retrieval, status checking, linking to payment acceptance, and managing the document verification lifecycle.
    try {
      PaymentAcceptance result = apiInstance.unlinkPaymentAcceptanceDocument(vaultId, paymentAcceptanceId, documentId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling VaultPaymentApi#unlinkPaymentAcceptanceDocument");
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
| **paymentAcceptanceId** | **UUID**| Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information. | |
| **documentId** | **UUID**| Unique identifier for a compliance document within a vault. Used for document retrieval, status checking, linking to payment acceptance, and managing the document verification lifecycle. | |

### Return type

[**PaymentAcceptance**](PaymentAcceptance.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Document successfully unlinked from payment acceptance. |  * Cache-Control -  <br>  |
| **400** | The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. |  -  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |

<a id="updatePaymentAcceptance"></a>
# **updatePaymentAcceptance**
> PaymentAcceptance updatePaymentAcceptance(vaultId, paymentAcceptanceId, paymentAcceptanceConfig)

Update Payment Acceptance

Modifies configuration of an existing payment acceptance.

### Example
```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.models.*;
import com.definancy.api.VaultPaymentApi;

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

    VaultPaymentApi apiInstance = new VaultPaymentApi(apiClient);
    String vaultId = "vaultId_example"; // String | Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates.
    UUID paymentAcceptanceId = UUID.randomUUID(); // UUID | Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information.
    PaymentAcceptanceConfig paymentAcceptanceConfig = new PaymentAcceptanceConfig(); // PaymentAcceptanceConfig | Partial payment acceptance configuration for updates. Can be used to modify the 'documents' list to add or remove compliance document associations, or to update 'order' information. Core price scenarios cannot be modified after  creation.
    try {
      PaymentAcceptance result = apiInstance.updatePaymentAcceptance(vaultId, paymentAcceptanceId, paymentAcceptanceConfig);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling VaultPaymentApi#updatePaymentAcceptance");
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
| **paymentAcceptanceId** | **UUID**| Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information. | |
| **paymentAcceptanceConfig** | [**PaymentAcceptanceConfig**](PaymentAcceptanceConfig.md)| Partial payment acceptance configuration for updates. Can be used to modify the &#39;documents&#39; list to add or remove compliance document associations, or to update &#39;order&#39; information. Core price scenarios cannot be modified after  creation. | |

### Return type

[**PaymentAcceptance**](PaymentAcceptance.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Payment acceptance updated successfully. |  * Cache-Control -  <br>  |
| **400** | The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. |  -  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |

<a id="vaultGetPaymentEstimate"></a>
# **vaultGetPaymentEstimate**
> PaymentEstimate vaultGetPaymentEstimate(vaultId, contractAmount)

Generate Payment Estimate

Calculates payment scenarios for specified contracts and amounts. Requires at least one contract-amount pair in the request body.

### Example
```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.models.*;
import com.definancy.api.VaultPaymentApi;

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

    VaultPaymentApi apiInstance = new VaultPaymentApi(apiClient);
    String vaultId = "vaultId_example"; // String | Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates.
    List<ContractAmount> contractAmount = Arrays.asList(); // List<ContractAmount> | List of contract-amount pairs for which to generate payment estimates. Must contain at least one contract-amount pair. Each contract must be subscribed to the vault. The amounts should represent the desired payment values in the contract's native units.
    try {
      PaymentEstimate result = apiInstance.vaultGetPaymentEstimate(vaultId, contractAmount);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling VaultPaymentApi#vaultGetPaymentEstimate");
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
| **contractAmount** | [**List&lt;ContractAmount&gt;**](ContractAmount.md)| List of contract-amount pairs for which to generate payment estimates. Must contain at least one contract-amount pair. Each contract must be subscribed to the vault. The amounts should represent the desired payment values in the contract&#39;s native units. | |

### Return type

[**PaymentEstimate**](PaymentEstimate.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Payment estimate generated successfully. |  * Cache-Control -  <br>  |
| **400** | The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. |  -  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |

