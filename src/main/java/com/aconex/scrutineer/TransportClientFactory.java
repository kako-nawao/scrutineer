package com.aconex.scrutineer;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public class TransportClientFactory {

    public TransportClient createTransportClient(ScrutineerCommandLineOptions commandLineOptions) {
        TransportClient client = new TransportClient(createSettings());
        client.addTransportAddress(new InetSocketTransportAddress(commandLineOptions.hostName, commandLineOptions.portNumber));
        return client;
    }

    Settings createSettings() {
        return ImmutableSettings.settingsBuilder()
                .build();
    }

}
