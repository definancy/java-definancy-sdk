package com.definancy.sdk.demo;

import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.api.NetworkApi;
import com.definancy.model.Network;

import java.util.List;

public class APIGetNetworks {
    public static void main(String[] args) throws Exception {
        ApiClient apiClient = Config.GetApiClient();
        NetworkApi api = new NetworkApi(apiClient);

        try {
            List<Network> networks = api.getNetworks();

            if (networks == null || networks.isEmpty()) {
                System.out.println("No networks found");
                return;
            }

            System.out.printf("Found %d networks:\n", networks.size());
            for (Network network : networks) {
                boolean enabled = Boolean.TRUE.equals(network.getConfig().getEnabled());
                System.out.printf(
                        "- %s is %s\n",
                        network.getInfo().getName(),
                        enabled ? "enabled" : "disabled"
                );
            }
        } catch (ApiException e) {
            Utils.printException(e, "NetworkApi", "getNetworks");
        } catch (Exception e) {
            Utils.printException(e, "NetworkApi", "getNetworks");
        }
    }
}
