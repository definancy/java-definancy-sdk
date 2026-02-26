package com.definancy.sdk.demo;

import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.api.AssetApi;
import com.definancy.model.Asset;
import com.definancy.model.AssetConfig;
import java.util.HashSet;
import java.util.Set;

public class APISetAsset {
    public static void main(String[] args) throws Exception {
        ApiClient apiClient = Config.GetApiClient();
        AssetApi api = new AssetApi(apiClient);

        AssetConfig assetConfig = new AssetConfig();
        assetConfig.setEnabled(true);

        Set<String> assets = new HashSet<>();
        Config.contracts.forEach(contract -> assets.add(contract.getAssetUnit()));

        try {
            for (String assetUnit : assets) {
                try {
                    Asset asset = api.configAsset(assetUnit, assetConfig);
                    boolean enabled = Boolean.TRUE.equals(asset.getConfig().getEnabled());
                    System.out.printf(
                            "- %s set to %s\n",
                            asset.getInfo().getName(),
                            enabled ? "enabled" : "disabled"
                    );
                } catch (ApiException e) {
                    System.err.printf("- %s error fetching asset data\n", assetUnit);
                    throw e;
                }
            }
        } catch (ApiException e) {
            Utils.printException(e, "AssetApi", "configAsset");
        } catch (Exception e) {
            Utils.printException(e, "AssetApi", "configAsset");
        }
    }
}
