package com.definancy.sdk.demo;

import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.api.NetworkApi;
import com.definancy.model.Network;
import com.definancy.model.NetworkConfig;
import java.util.HashSet;
import java.util.Set;

public class APISetNetwork {
    public static void main(String[] args) throws Exception {
        ApiClient apiClient = Config.GetApiClient();
        NetworkApi api = new NetworkApi(apiClient);

        NetworkConfig networkConfig = new NetworkConfig();
        networkConfig.setEnabled(true);

        Set<String> networks = new HashSet<>();
        Config.contracts.forEach(contract -> networks.add(contract.getNetworkId()));

        try {
            for (String networkId : networks) {
                try {
                    Network network = api.configNetwork(networkId, networkConfig);
                    boolean enabled = Boolean.TRUE.equals(network.getConfig().getEnabled());
                    System.out.printf(
                            "- %s set to %s\n",
                            network.getInfo().getName(),
                            enabled ? "enabled" : "disabled"
                    );
                } catch (ApiException e) {
                    System.err.printf("- %s error fetching network data\n", networkId);
                    throw e;
                }
            }
        } catch (ApiException e) {
            Utils.printException(e, "NetworkApi", "configNetwork");
        } catch (Exception e) {
            Utils.printException(e, "NetworkApi", "configNetwork");
        }
    }
}
