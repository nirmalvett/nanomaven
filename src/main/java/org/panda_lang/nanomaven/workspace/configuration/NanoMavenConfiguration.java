/*
 * Copyright (c) 2017 Dzikoysk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.panda_lang.nanomaven.workspace.configuration;

import org.apache.commons.io.IOUtils;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

public class NanoMavenConfiguration {

    private static final String FILENAME = "nanomaven.yml";

    private int port;

    private String hostname;

    private boolean debugModeEnabled;

    private List<String> repositories;

    private boolean repositoryPathEnabled;

    private boolean indexingEnabled;

    private boolean nestedMaven;

    private String externalMaven;

    private boolean deployEnabled;

    private boolean authorizationEnabled;

    private boolean authorizationEnabledGet;

    private List<String> administrators;

    private boolean useLocalMetadatas;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public boolean isDebugModeEnabled() {
        return debugModeEnabled;
    }

    public void setDebugModeEnabled(boolean debugModeEnabled) {
        this.debugModeEnabled = debugModeEnabled;
    }

    public List<String> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<String> repositories) {
        this.repositories = repositories;
    }

    public boolean isRepositoryPathEnabled() {
        return repositoryPathEnabled;
    }

    public void setRepositoryPathEnabled(boolean repositoryPathEnabled) {
        this.repositoryPathEnabled = repositoryPathEnabled;
    }

    public boolean isIndexingEnabled() {
        return indexingEnabled;
    }

    public void setIndexingEnabled(boolean indexingEnabled) {
        this.indexingEnabled = indexingEnabled;
    }

    public boolean isNestedMaven() {
        return nestedMaven;
    }

    public void setNestedMaven(boolean nestedMaven) {
        this.nestedMaven = nestedMaven;
    }

    public String getExternalMaven() {
        return externalMaven;
    }

    public void setExternalMaven(String externalMaven) {
        this.externalMaven = externalMaven;
    }

    public boolean isDeployEnabled() {
        return deployEnabled;
    }

    public void setDeployEnabled(boolean deployEnabled) {
        this.deployEnabled = deployEnabled;
    }

    public boolean isAuthorizationEnabled() {
        return authorizationEnabled;
    }

    public void setAuthorizationEnabled(boolean authorizationEnabled) {
        this.authorizationEnabled = authorizationEnabled;
    }

    public boolean isAuthorizationEnabledGet() {
        return authorizationEnabledGet;
    }

    public void setAuthorizationEnabledGet(boolean authorizationEnabledGet) {
        this.authorizationEnabledGet = authorizationEnabledGet;
    }

    public List<String> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(List<String> administrators) {
        this.administrators = administrators;
    }

    public boolean isUseLocalMetadatas() {
        return useLocalMetadatas;
    }

    public void setUseLocalMetadatas(boolean useLocalMetadatas) {
        this.useLocalMetadatas = useLocalMetadatas;
    }

    public static NanoMavenConfiguration load() {
    	File configFile = new File(FILENAME);
        Representer representer = new Representer();
        representer.getPropertyUtils().setSkipMissingProperties(true);
        Yaml yaml = new Yaml(representer);
        NanoMavenConfiguration configuration;

        try (InputStream input = new FileInputStream(configFile)) {
            configuration = yaml.loadAs(input, NanoMavenConfiguration.class);
        } catch (Exception ex) {
        	throw new RuntimeException("Could not load configuration", ex);
        }

        return configuration;
    }

}