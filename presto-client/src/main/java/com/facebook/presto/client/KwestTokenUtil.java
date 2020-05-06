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

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.util.Optional;

public class KwestTokenUtil
{
    private OkHttpClient client;

    public KwestTokenUtil(String keystorePath, String keystorePass)
    {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        OkHttpUtil.setupSsl(builder, Optional.of(keystorePath),
                Optional.of(keystorePass), Optional.empty(), Optional.empty());
        client = builder.build();
    }

    public String getToken(String loginUrl, String user, String group)
            throws Exception
    {
        String url = HttpUrl.parse(loginUrl).newBuilder()
                .addQueryParameter("user", user)
                .addQueryParameter("group", group)
                .addQueryParameter("credential", "")
                .build().toString();

        RequestBody formBody = new FormBody.Builder()
                .build();

        Request request = new Request.Builder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("x-kwest-authn", "MT")
                .url(url)
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        Response resp = call.execute();
        return resp.body().string();
    }
}
