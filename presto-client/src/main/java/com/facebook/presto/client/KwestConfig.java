/*
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
package com.facebook.presto.client;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;

public class KwestConfig
{
    private String user;
    private String group;
    private String clientCertificates;
    private String clientKey;
    private String kwestCatalog;
    private String kwestSchema;
    private String kwestServiceEndpoint;
    private Integer autoSuggestionLimit;
    private ObjectMapper mapper;

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getGroup()
    {
        return group;
    }

    public void setGroup(String group)
    {
        this.group = group;
    }

    public String getClientCertificates()
    {
        return clientCertificates;
    }

    public void setClientCertificates(String clientCertificates)
    {
        this.clientCertificates = clientCertificates;
    }

    public String getClientKey()
    {
        return clientKey;
    }

    public void setClientKey(String clientKey)
    {
        this.clientKey = clientKey;
    }

    public String getKwestCatalog()
    {
        return kwestCatalog;
    }

    public void setKwestCatalog(String kwestCatalog)
    {
        this.kwestCatalog = kwestCatalog;
    }

    public String getKwestSchema()
    {
        return kwestSchema;
    }

    public void setKwestSchema(String kwestSchema)
    {
        this.kwestSchema = kwestSchema;
    }

    public String getKwestServiceEndpoint()
    {
        return kwestServiceEndpoint;
    }

    public void setKwestServiceEndpoint(String kwestServiceEndpoint)
    {
        this.kwestServiceEndpoint = kwestServiceEndpoint;
    }

    public Integer getAutoSuggestionLimit()
    {
        return autoSuggestionLimit;
    }

    public void setAutoSuggestionLimit(Integer autoSuggestionLimit)
    {
        this.autoSuggestionLimit = autoSuggestionLimit;
    }

    public static KwestConfig load(String configPath)
    {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        KwestConfig conf = null;
        try {
            conf = mapper.readValue(new File(configPath), KwestConfig.class);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conf;
    }
}
