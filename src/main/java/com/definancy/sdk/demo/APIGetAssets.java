package com.definancy.sdk.demo;

import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.api.AssetApi;
import com.definancy.model.Asset;
import java.util.List;

public class APIGetAssets {
    public static void main(String[] args) throws Exception {
        ApiClient apiClient = Config.GetApiClient();
        AssetApi api = new AssetApi(apiClient);

        try {
            List<Asset> assets = api.getAssets();

            if (assets == null || assets.isEmpty()) {
                System.out.println("No assets found");
                return;
            }

            System.out.printf("Found %d assets:\n", assets.size());
            for (Asset asset : assets) {
                boolean enabled = Boolean.TRUE.equals(asset.getConfig().getEnabled());
                System.out.printf(
                        "- %s is %s\n",
                        asset.getInfo().getName(),
                        enabled ? "enabled" : "disabled"
                );
            }
        } catch (ApiException e) {
            Utils.printException(e, "AssetApi", "getAssets");
        } catch (Exception e) {
            Utils.printException(e, "AssetApi", "getAssets");
        }
    }
}
