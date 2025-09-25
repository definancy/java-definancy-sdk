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

            if (assets.isEmpty()) {
                System.out.println("No assets found");
                return;
            }

            System.out.printf("Found %d assets:\n", assets.size());
            for (Asset asset : assets) {
               System.out.println(asset.getInfo().getName());
            }
        } catch (ApiException e) {
            Utils.printApiException(e, "AssetApi", "getAssets");
        }
    }
}
