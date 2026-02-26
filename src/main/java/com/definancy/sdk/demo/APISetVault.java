package com.definancy.sdk.demo;

import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.api.VaultApi;
import com.definancy.model.Vault;
import com.definancy.model.VaultConfig;

public class APISetVault {
    public static void main(String[] args) throws Exception {
        ApiClient apiClient = Config.GetApiClient();
        VaultApi api = new VaultApi(apiClient);

        VaultConfig vaultConfig = new VaultConfig();
        vaultConfig.setEnabled(true);
        vaultConfig.setContractIds(Config.contracts);

        try {
            Vault vault = api.setVault(Config.vaultId, vaultConfig);
            System.out.println(vault);
            System.out.println("Vault set successfully");
        } catch (ApiException e) {
            Utils.printException(e, "VaultApi", "setVault");
        } catch (Exception e) {
            Utils.printException(e, "VaultApi", "setVault");
        }
    }
}
