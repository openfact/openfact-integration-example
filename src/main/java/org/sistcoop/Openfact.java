package org.sistcoop;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

import javax.net.ssl.SSLContext;
import java.net.URI;

import static org.keycloak.OAuth2Constants.PASSWORD;

/**
 * Provides a Openfact client. By default, this implementation uses a {@link ResteasyClient RESTEasy client} with the
 * default {@link ResteasyClientBuilder} settings. To customize the underling client, use a {@link OpenfactBuilder} to
 * create a Openfact client.
 *
 * @see OpenfactBuilder
 */
public class Openfact {

    private final Keycloak keycloakClient;
    private final ResteasyWebTarget target;
    private final ResteasyClient client;

    public Openfact(String serverUrl, String organization, Keycloak keycloakClient, ResteasyClient client) {
        this.keycloakClient = keycloakClient;
        this.target = null;
        this.client = null;
    }

    public OrganizationsResource organizations() {
        return target.proxy(OrganizationsResource.class);
    }

    public OrganizationResource organization(String organizationName) {
        return organizations().organization(organizationName);
    }

    public ServerInfoResource serverInfo() {
        return target.proxy(ServerInfoResource.class);
    }

    /**
     * Create a secure proxy based on an absolute URI.
     * All set up with appropriate token
     *
     * @param proxyClass
     * @param absoluteURI
     * @param <T>
     * @return
     */
    public <T> T proxy(Class<T> proxyClass, URI absoluteURI) {
        return client.target(absoluteURI).proxy(proxyClass);
    }

    /**
     * Closes the underlying client. After calling this method, this <code>Openfact</code> instance cannot be reused.
     */
    public void close() {
        client.close();
    }

}