package com.definancy.sdk.demo;

import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.api.ContractApi;
import com.definancy.model.Contract;
import com.definancy.model.ContractId;
import com.definancy.model.ContractConfig;

public class APISetContract {
    public static void main(String[] args) throws Exception {
        ApiClient apiClient = Config.GetApiClient();
        ContractApi api = new ContractApi(apiClient);

        ContractConfig contractConfig = new ContractConfig();
        contractConfig.setEnabled(true);

        try {
            for (ContractId contractId : Config.contracts) {
                try {
                    Contract contract = api.configContract(contractId.getAssetUnit(), contractId.getNetworkId(), contractConfig);
                    boolean enabled = Boolean.TRUE.equals(contract.getConfig().getEnabled());
                    System.out.printf(
                            "- %s@%s set to %s\n",
                            contract.getId().getAssetUnit(),
                            contract.getId().getNetworkId(),
                            enabled ? "enabled" : "disabled"
                    );
                } catch (ApiException e) {
                    System.err.printf("- %s@%s error fetching contract data\n",
                            contractId.getAssetUnit(),
                            contractId.getNetworkId()
                    );
                    throw e;
                }
            }
        } catch (ApiException e) {
            Utils.printException(e, "ContractApi", "configContract");
        } catch (Exception e) {
            Utils.printException(e, "ContractApi", "configContract");
        }
    }
}
