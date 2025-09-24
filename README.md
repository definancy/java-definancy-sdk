# definancy-sdk

Definancy Node API
- API version: 0.0.1.wip.20250826
  - Build date: 2025-09-24T14:07:22.312732801Z[Etc/UTC]
  - Generator version: 7.16.0-SNAPSHOT

REST API for managing asset, network and contract configurations, and vault-based financial operations.
This API provides a comprehensive set of endpoints for health monitoring, network management, asset 
configuration, contract management, vault lifecycle management, and QR-Code generation. It is designed
to facilitate seamless integration with financial systems, ensuring secure and efficient payment 
processing while maintaining strict compliance standards.


*Automatically generated*


## Requirements

Building the API client library requires:
1. Java 1.8+
2. Maven (3.8.3+)/Gradle (7.2+)

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn clean install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn clean deploy
```

Refer to the [OSSRH Guide](http://central.sonatype.org/pages/ossrh-guide.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>com.definancy.sdk</groupId>
  <artifactId>definancy-sdk</artifactId>
  <version>1.0.0</version>
  <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
  repositories {
    mavenCentral()     // Needed if the 'definancy-sdk' jar has been published to maven central.
    mavenLocal()       // Needed if the 'definancy-sdk' jar has been published to the local maven repo.
  }

  dependencies {
     implementation "com.definancy.sdk:definancy-sdk:1.0.0"
  }
```

### Others

At first generate the JAR by executing:

```shell
mvn clean package
```

Then manually install the following JARs:

* `target/definancy-sdk-1.0.0.jar`
* `target/lib/*.jar`

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.model.*;
import com.definancy.api.AssetApi;

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

    AssetApi apiInstance = new AssetApi(apiClient);
    String assetUnit = "assetUnit_example"; // String | Ticker symbol for a digital asset (e.g., 'EUR', 'USDC', 'ETH', 'BTC'). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset.
    AssetConfig assetConfig = new AssetConfig(); // AssetConfig | Asset configuration parameters to update. Currently supports only the 'enabled'  field for controlling asset availability. When setting 'enabled' to false, the  asset will be disabled for payment processing while preserving all existing  contracts and historical transaction data.
    try {
      Asset result = apiInstance.configAsset(assetUnit, assetConfig);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AssetApi#configAsset");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}

```

## Documentation for API Endpoints

All URIs are relative to *https://definancy.xboshy.io*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*AssetApi* | [**configAsset**](docs/AssetApi.md#configAsset) | **PATCH** /api/v1/asset/{assetUnit} | Configure Asset
*AssetApi* | [**getAsset**](docs/AssetApi.md#getAsset) | **GET** /api/v1/asset/{assetUnit} | Get Asset
*AssetApi* | [**getAssets**](docs/AssetApi.md#getAssets) | **GET** /api/v1/asset | List Assets
*ContractApi* | [**configContract**](docs/ContractApi.md#configContract) | **PATCH** /api/v1/contract/{assetUnit}/{networkId} | Configure Contract
*ContractApi* | [**getContract**](docs/ContractApi.md#getContract) | **GET** /api/v1/contract/{assetUnit}/{networkId} | Get Contract
*ContractApi* | [**getContracts**](docs/ContractApi.md#getContracts) | **GET** /api/v1/contract | List Contracts
*ExperimentalApi* | [**registerDid**](docs/ExperimentalApi.md#registerDid) | **PUT** /api/v1/did/{definancyId} | Register a Definancy DID
*NetworkApi* | [**configNetwork**](docs/NetworkApi.md#configNetwork) | **PATCH** /api/v1/network/{networkId} | Configure Network
*NetworkApi* | [**getNetwork**](docs/NetworkApi.md#getNetwork) | **GET** /api/v1/network/{networkId} | Get Network
*NetworkApi* | [**getNetworkNativeAsset**](docs/NetworkApi.md#getNetworkNativeAsset) | **GET** /api/v1/network/{networkId}/native | Get Network Native Asset
*NetworkApi* | [**getNetworks**](docs/NetworkApi.md#getNetworks) | **GET** /api/v1/network | List Networks
*ProbeApi* | [**healthyCheck**](docs/ProbeApi.md#healthyCheck) | **GET** /api/v1/healthy | Health Check
*ProbeApi* | [**readyCheck**](docs/ProbeApi.md#readyCheck) | **GET** /api/v1/ready | Ready Check
*QrCodeApi* | [**generateWalletQrCodes**](docs/QrCodeApi.md#generateWalletQrCodes) | **POST** /api/v1/qrcode/transaction | Generate QR-Codes for wallets
*VaultApi* | [**archiveVault**](docs/VaultApi.md#archiveVault) | **DELETE** /api/v1/vault/{vaultId} | Archive Vault
*VaultApi* | [**configVault**](docs/VaultApi.md#configVault) | **PATCH** /api/v1/vault/{vaultId} | Partially Update Vault Configuration
*VaultApi* | [**getVault**](docs/VaultApi.md#getVault) | **GET** /api/v1/vault/{vaultId} | Get Vault
*VaultApi* | [**getVaults**](docs/VaultApi.md#getVaults) | **GET** /api/v1/vault | List Vaults
*VaultApi* | [**setVault**](docs/VaultApi.md#setVault) | **PUT** /api/v1/vault/{vaultId} | Create or Update Vault
*VaultContractSubscriptionApi* | [**vaultSubscribeContract**](docs/VaultContractSubscriptionApi.md#vaultSubscribeContract) | **PUT** /api/v1/vault/{vaultId}/contract/{assetUnit}/{networkId} | Subscribe Contract to Vault
*VaultContractSubscriptionApi* | [**vaultUnsubscribeContract**](docs/VaultContractSubscriptionApi.md#vaultUnsubscribeContract) | **DELETE** /api/v1/vault/{vaultId}/contract/{assetUnit}/{networkId} | Unsubscribe Contract from Vault
*VaultDocumentApi* | [**archiveDocument**](docs/VaultDocumentApi.md#archiveDocument) | **DELETE** /api/v1/vault/{vaultId}/document/{documentId} | Archive Document
*VaultDocumentApi* | [**getDocument**](docs/VaultDocumentApi.md#getDocument) | **GET** /api/v1/vault/{vaultId}/document/{documentId} | Get Document
*VaultDocumentApi* | [**submitDocument**](docs/VaultDocumentApi.md#submitDocument) | **PUT** /api/v1/vault/{vaultId}/document | Submit Document
*VaultPaymentApi* | [**archivePaymentAcceptance**](docs/VaultPaymentApi.md#archivePaymentAcceptance) | **DELETE** /api/v1/vault/{vaultId}/payment/acceptance/{paymentAcceptanceId} | Archive Payment Acceptance
*VaultPaymentApi* | [**createPaymentAcceptance**](docs/VaultPaymentApi.md#createPaymentAcceptance) | **PUT** /api/v1/vault/{vaultId}/payment/acceptance | Create Payment Acceptance
*VaultPaymentApi* | [**getPaymentAcceptance**](docs/VaultPaymentApi.md#getPaymentAcceptance) | **GET** /api/v1/vault/{vaultId}/payment/acceptance/{paymentAcceptanceId} | Get Payment Acceptance
*VaultPaymentApi* | [**linkPaymentAcceptanceDocument**](docs/VaultPaymentApi.md#linkPaymentAcceptanceDocument) | **PUT** /api/v1/vault/{vaultId}/payment/acceptance/{paymentAcceptanceId}/document/{documentId} | Link Document to Payment Acceptance
*VaultPaymentApi* | [**unlinkPaymentAcceptanceDocument**](docs/VaultPaymentApi.md#unlinkPaymentAcceptanceDocument) | **DELETE** /api/v1/vault/{vaultId}/payment/acceptance/{paymentAcceptanceId}/document/{documentId} | Unlink Document from Payment Acceptance
*VaultPaymentApi* | [**updatePaymentAcceptance**](docs/VaultPaymentApi.md#updatePaymentAcceptance) | **PATCH** /api/v1/vault/{vaultId}/payment/acceptance/{paymentAcceptanceId} | Update Payment Acceptance
*VaultPaymentApi* | [**vaultGetPaymentEstimate**](docs/VaultPaymentApi.md#vaultGetPaymentEstimate) | **POST** /api/v1/vault/{vaultId}/payment/estimate | Generate Payment Estimate


## Documentation for Models

 - [AcceptancePaymentTrait](docs/AcceptancePaymentTrait.md)
 - [AcceptancePriceTrait](docs/AcceptancePriceTrait.md)
 - [Amount](docs/Amount.md)
 - [AmountRaw](docs/AmountRaw.md)
 - [AmountValue](docs/AmountValue.md)
 - [Asset](docs/Asset.md)
 - [AssetConfig](docs/AssetConfig.md)
 - [AssetInfo](docs/AssetInfo.md)
 - [AssetMediaMap](docs/AssetMediaMap.md)
 - [AssetStatus](docs/AssetStatus.md)
 - [BlockchainConfirmationStats](docs/BlockchainConfirmationStats.md)
 - [BlockchainTransaction](docs/BlockchainTransaction.md)
 - [BlockchainTransactionStatus](docs/BlockchainTransactionStatus.md)
 - [Compliance](docs/Compliance.md)
 - [ComplianceScenario](docs/ComplianceScenario.md)
 - [ComplianceScenarioStatus](docs/ComplianceScenarioStatus.md)
 - [Contract](docs/Contract.md)
 - [ContractAmount](docs/ContractAmount.md)
 - [ContractConfig](docs/ContractConfig.md)
 - [ContractId](docs/ContractId.md)
 - [ContractInfo](docs/ContractInfo.md)
 - [ContractMediaMap](docs/ContractMediaMap.md)
 - [ContractStatus](docs/ContractStatus.md)
 - [ContractTraits](docs/ContractTraits.md)
 - [CustodialV1](docs/CustodialV1.md)
 - [Document](docs/Document.md)
 - [DocumentConfig](docs/DocumentConfig.md)
 - [DocumentData](docs/DocumentData.md)
 - [DocumentInfo](docs/DocumentInfo.md)
 - [DocumentStatus](docs/DocumentStatus.md)
 - [DocumentStatusId](docs/DocumentStatusId.md)
 - [DocumentType](docs/DocumentType.md)
 - [Error](docs/Error.md)
 - [ErrorList](docs/ErrorList.md)
 - [Expire](docs/Expire.md)
 - [Media](docs/Media.md)
 - [MediaMap](docs/MediaMap.md)
 - [MediaType](docs/MediaType.md)
 - [Network](docs/Network.md)
 - [NetworkConfig](docs/NetworkConfig.md)
 - [NetworkInfo](docs/NetworkInfo.md)
 - [NetworkMediaMap](docs/NetworkMediaMap.md)
 - [NetworkStatus](docs/NetworkStatus.md)
 - [NonCustodialV1](docs/NonCustodialV1.md)
 - [PaymentAcceptance](docs/PaymentAcceptance.md)
 - [PaymentAcceptanceConfig](docs/PaymentAcceptanceConfig.md)
 - [PaymentAcceptanceInfo](docs/PaymentAcceptanceInfo.md)
 - [PaymentAcceptanceOrder](docs/PaymentAcceptanceOrder.md)
 - [PaymentAcceptanceScenario](docs/PaymentAcceptanceScenario.md)
 - [PaymentAcceptanceScenarioStatus](docs/PaymentAcceptanceScenarioStatus.md)
 - [PaymentAcceptanceStatus](docs/PaymentAcceptanceStatus.md)
 - [PaymentAcceptanceStatusId](docs/PaymentAcceptanceStatusId.md)
 - [PaymentEstimate](docs/PaymentEstimate.md)
 - [PaymentEstimateScenario](docs/PaymentEstimateScenario.md)
 - [PersonAddress](docs/PersonAddress.md)
 - [PersonLegalId](docs/PersonLegalId.md)
 - [PersonLegalIdType](docs/PersonLegalIdType.md)
 - [PersonLegalIncorporation](docs/PersonLegalIncorporation.md)
 - [PersonLegalV1](docs/PersonLegalV1.md)
 - [PersonNaturalBirth](docs/PersonNaturalBirth.md)
 - [PersonNaturalId](docs/PersonNaturalId.md)
 - [PersonNaturalIdType](docs/PersonNaturalIdType.md)
 - [PersonNaturalV1](docs/PersonNaturalV1.md)
 - [PersonType](docs/PersonType.md)
 - [PersonV1](docs/PersonV1.md)
 - [QrCode](docs/QrCode.md)
 - [QrCodeEncoding](docs/QrCodeEncoding.md)
 - [QrCodeType](docs/QrCodeType.md)
 - [Status](docs/Status.md)
 - [TraitProperties](docs/TraitProperties.md)
 - [Traits](docs/Traits.md)
 - [Vault](docs/Vault.md)
 - [VaultConfig](docs/VaultConfig.md)
 - [VaultInfo](docs/VaultInfo.md)
 - [Version](docs/Version.md)


<a id="documentation-for-authorization"></a>
## Documentation for Authorization


Authentication schemes defined for the API:
<a id="dpop-auth"></a>
### dpop-auth

- **Type**: API key
- **API key parameter name**: Authorization
- **Location**: HTTP header

<a id="dpop-proof"></a>
### dpop-proof

- **Type**: API key
- **API key parameter name**: DPoP
- **Location**: HTTP header


## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author



