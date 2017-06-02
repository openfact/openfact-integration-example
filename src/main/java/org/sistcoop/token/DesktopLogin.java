/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sistcoop.token;

import org.keycloak.OAuthErrorException;
import org.keycloak.adapters.ServerRequest;
import org.keycloak.adapters.installed.KeycloakInstalled;
import org.keycloak.common.VerificationException;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author carlosthe19916@sistcoop.com
 */
public class DesktopLogin {
    
    public static void main(String args[]) throws IOException, ServerRequest.HttpFailure, VerificationException, InterruptedException, OAuthErrorException, URISyntaxException {
        KeycloakInstalled keycloakInstalled = new KeycloakInstalled();
        keycloakInstalled.loginDesktop();
    }
    
}