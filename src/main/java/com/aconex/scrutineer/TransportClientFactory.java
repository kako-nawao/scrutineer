package com.aconex.scrutineer;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public class TransportClientFactory {

    public TransportClient createTransportClient(ScrutineerCommandLineOptions commandLineOptions) {
        TransportClient client = new TransportClient(createSettings(commandLineOptions));
        client.addTransportAddress(new InetSocketTransportAddress(commandLineOptions.hostName, 9300));
        return client;
    }

    Settings createSettings(ScrutineerCommandLineOptions commandLineOptions) {
        return ImmutableSettings.settingsBuilder()
                .put("cluster.name", commandLineOptions.clusterName)
                .build();
    }

}
