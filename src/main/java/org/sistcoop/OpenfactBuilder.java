package org.sistcoop;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;

/**
 * Provides a {@link Openfact} client builder with the ability to customize the underlying
 * {@link ResteasyClient RESTEasy client} used to communicate with the Openfact server.
 * <p>
 * <p>Example usage with a connection pool size of 20:</p>
 * <pre>
 *   Openfact openfact = OpenfactBuilder.builder()
 *     .serverUrl("https://openfact.example.com/openfact")
 *     .organization("organization")
 *     .keycloakClient(new Keycloak())
 *     .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(20).build())
 *     .build();
 * </pre>
 *
 * @see ResteasyClientBuilder
 */
public class OpenfactBuilder {

    private String serverUrl;
    private String organization;

    private Keycloak keycloakClient;
    private ResteasyClient resteasyClient;

    public OpenfactBuilder serverUrl(String serverUrl) {
        this.serverUrl = serverUrl;
        return this;
    }

    public OpenfactBuilder organization(String organization) {
        this.organization = organization;
        return this;
    }

    public OpenfactBuilder keycloakClient(Keycloak keycloakClient) {
        this.keycloakClient = keycloakClient;
        return this;
    }

    public OpenfactBuilder resteasyClient(ResteasyClient resteasyClient) {
        this.resteasyClient = resteasyClient;
        return this;
    }

    /**
     * Builds a new Openfact client from this builder.
     */
    public Openfact build() {
        if (serverUrl == null) {
            throw new IllegalStateException("serverUrl required");
        }

        if (organization == null) {
            throw new IllegalStateException("organization required");
        }

        return new Openfact(serverUrl, organization, keycloakClient, resteasyClient);
    }

    private OpenfactBuilder() {
    }

    /**
     * Returns a new Openfact builder.
     */
    public static OpenfactBuilder builder() {
        return new OpenfactBuilder();
    }
}