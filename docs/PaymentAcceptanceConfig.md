

# PaymentAcceptanceConfig

Payment acceptance configuration parameters including commercial order  context, payment scenarios, and document associations. Defines the  structure and requirements for payment processing operations.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**order** | [**PaymentAcceptanceOrder**](PaymentAcceptanceOrder.md) |  |  [optional] |
|**priceScenarios** | [**List&lt;ContractAmount&gt;**](ContractAmount.md) | Required payment obligations as contract-amount pairs. Must contain  at least one scenario when creating new payment acceptance. |  [optional] |
|**documents** | **List&lt;UUID&gt;** | Optional list of document IDs to associate with this payment  acceptance for compliance purposes. |  [optional] |



