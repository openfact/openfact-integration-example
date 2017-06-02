/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openfact.admin;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.BearerAuthFilter;
import org.openfact.admin.client.resource.OrganizationResource;
import org.openfact.admin.client.resource.OrganizationsResource;
import org.openfact.admin.client.resource.ServerInfoResource;

import java.net.URI;

public class Openfact {

    private final Config config;
    private final Keycloak keycloakClient;
    private final ResteasyWebTarget target;
    private final ResteasyClient client;

    Openfact(String serverUrl, Keycloak keycloakClient, ResteasyClient resteasyClient) {
        this.config = new Config(serverUrl);
        this.client = resteasyClient != null ? resteasyClient : new ResteasyClientBuilder().connectionPoolSize(10).build();
        this.keycloakClient = keycloakClient;

        this.client.register(newAuthFilter());
        this.target = client.target(config.getServerUrl());
        //this.target.register(newAuthFilter());
    }

    private BearerAuthFilter newAuthFilter() {
        return new BearerAuthFilter(keycloakClient.tokenManager());
    }

    public ResteasyWebTarget target(String uri) {
        return client.target(uri);
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
