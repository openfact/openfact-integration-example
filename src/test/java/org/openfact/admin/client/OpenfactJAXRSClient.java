package org.openfact.admin.client;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.openfact.admin.Openfact;
import org.openfact.admin.OpenfactBuilder;
import org.openfact.admin.client.resource.OrganizationsResource;
import org.openfact.admin.client.resource.ServerInfoResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OpenfactJAXRSClient implements TestRule {

    private Openfact openfact;

    private static final Properties ofProperties = new Properties();
    private static final Properties kcProperties = new Properties();

    static {
        InputStream ofIs = OpenfactJAXRSClient.class.getClassLoader().getResourceAsStream("META-INF/openfact.properties");
        InputStream kcIs = OpenfactJAXRSClient.class.getClassLoader().getResourceAsStream("META-INF/keycloak.properties");

        try {
            ofProperties.load(ofIs);
        } catch (IOException e) {
            throw new IllegalStateException("Invalid Openfact configuration");
        }

        try {
            kcProperties.load(kcIs);
        } catch (IOException e) {
            throw new IllegalStateException("Invalid Keycloak configuration");
        }
    }

    private OpenfactJAXRSClient() throws IOException {
        this(ofProperties.getProperty("serverUrl"));
    }

    private OpenfactJAXRSClient(String serverUrl) throws IOException {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(kcProperties.getProperty("serverUrl"))
                .realm(kcProperties.getProperty("realm"))
                .username(kcProperties.getProperty("username"))
                .password(kcProperties.getProperty("password"))
                .clientId(kcProperties.getProperty("clientId"))
                .clientSecret(kcProperties.getProperty("clientSecret")).build();

        openfact = OpenfactBuilder.builder().serverUrl(serverUrl).keycloakClient(keycloak).build();
    }

    private OpenfactJAXRSClient(String serverUrl, Keycloak keycloak) throws IOException {
        openfact = OpenfactBuilder.builder().serverUrl(serverUrl).keycloakClient(keycloak).build();
    }

    public static OpenfactJAXRSClient buildTarget() {
        try {
            return new OpenfactJAXRSClient();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static OpenfactJAXRSClient buildTarget(String serverUrl) {
        try {
            return new OpenfactJAXRSClient(serverUrl);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static OpenfactJAXRSClient buildTarget(String serverUrl, Keycloak keycloak) {
        try {
            return new OpenfactJAXRSClient(serverUrl, keycloak);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public OrganizationsResource organizations() {
        return openfact.organizations();
    }

    public ServerInfoResource serverInfo() {
        return openfact.serverInfo();
    }

    public ResteasyWebTarget target(String uri) {
        return openfact.target(uri);
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                base.evaluate();
            }
        };
    }

}
