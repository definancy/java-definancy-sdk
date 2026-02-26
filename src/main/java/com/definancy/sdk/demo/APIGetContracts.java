package com.definancy.sdk.demo;

import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.api.ContractApi;
import com.definancy.model.Contract;
import java.util.List;

public class APIGetContracts {
    public static void main(String[] args) throws Exception {
        ApiClient apiClient = Config.GetApiClient();
        ContractApi api = new ContractApi(apiClient);

        try {
            List<Contract> contracts = api.getContracts();

            if (contracts == null || contracts.isEmpty()) {
                System.out.println("No assets found");
                return;
            }

            System.out.printf("Found %d contracts (asset@network):\n", contracts.size());
            for (Contract contract : contracts) {
                boolean enabled = Boolean.TRUE.equals(contract.getConfig().getEnabled());
                System.out.printf(
                        "- %s@%s is %s\n",
                        contract.getId().getAssetUnit(),
                        contract.getId().getNetworkId(),
                        enabled ? "enabled" : "disabled"
                );
            }
        } catch (ApiException e) {
            Utils.printException(e, "ContractApi", "getContracts");
        } catch (Exception e) {
            Utils.printException(e, "ContractApi", "getContracts");
        }
    }
}
