package com.definancy.sdk.ra;

import javax.resource.ResourceException;
import javax.resource.spi.*;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.transaction.xa.XAResource;

/**
 * Minimal Resource Adapter that provides classloader isolation
 * without implementing full JCA functionality.
 */
@Connector(displayName = "REST Client Adapter", vendorName = "Your Company", eisType = "REST API Client", version = "1.0")
public class PassthroughResourceAdapter implements ResourceAdapter {

    @Override
    public void start(BootstrapContext ctx) throws ResourceAdapterInternalException {
        // Nothing to initialize
    }

    @Override
    public void stop() {
        // Nothing to clean up
    }

    @Override
    public void endpointActivation(MessageEndpointFactory endpointFactory,
            ActivationSpec spec) throws ResourceException {
        throw new UnsupportedOperationException("Inbound not supported");
    }

    @Override
    public void endpointDeactivation(MessageEndpointFactory endpointFactory,
            ActivationSpec spec) {
        // Nothing to do
    }

    @Override
    public XAResource[] getXAResources(ActivationSpec[] specs) throws ResourceException {
        return new XAResource[0];
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PassthroughResourceAdapter;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
