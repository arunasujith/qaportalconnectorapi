package org.wso2.carbon.utility.qaportal.dss.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLHandshakeException;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

/**
 * Created by kavith on 2/3/14.
 */
public class HttpClientWrapper {

    private Log log = LogFactory.getLog(HttpClientWrapper.class);

    private String transport;

    private String host;

    private int port;

    private String userName;

    private String password;

    public HttpClientWrapper(String transport, String host, int port, String userName, String password)
    {
        this.transport  = transport;
        this.host       = host;
        this.password   = password;
        this.port       = port;
        this.userName   = userName;
    }

    /**
     * Create a HTTP get request and returns JSON response
     *
     * @param service   DSS Service name
     * @param resource  rest resource identifier
     *
     * @return json response from the server
     *
     * @throws IOException
     */
    public String get(String service, String resource) throws IOException
    {
        String jsonResponse = "{}";

        String url = transport + "://" + host + ":" + port + "/services/" + service + "/" + resource;

        HttpGet request = new HttpGet(url);

        request.addHeader("User-Agent", "QAPortalDSSClient/1.0.0");
        request.addHeader("Accept", "application/json");
        request.addHeader("Authorization", "Basic "+getBasicAuthHeader());

        HttpResponse response = (new DefaultHttpResponseFactory()).newHttpResponse(request.getProtocolVersion(),
                                                                                    404, new HttpClientContext());

        try
        {
            response = createClient().execute(request);

            log.debug("QAPortalDSSClient : HttpClientWrapper - Successfully executed the request to " + url + ".");
        }
        catch (SSLHandshakeException ex)
        {
            log.debug("QAPortalDSSClient : HttpClientWrapper - Error executing the request." +
                    "\t Retrying to execute with a custom ssl context.");

            // Host uses a certificate which is not issued by a certification authority.
            // Testing servers are hosted locally
            try
            {
                response = createClientWithCustomSSLContext().execute(request);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        jsonResponse = EntityUtils.toString(response.getEntity());

        return jsonResponse;
    }

    private HttpClient createClient(){

        return HttpClientBuilder.create().build();

    }

    private HttpClient createClientWithCustomSSLContext() throws Exception {

        KeyStore trustStore  = KeyStore.getInstance(KeyStore.getDefaultType());
        File keyStore = new File("my.keystore");
        keyStore.createNewFile();

        FileInputStream inputStream = new FileInputStream(keyStore);
        try
        {
            trustStore.load(inputStream, "nopassword".toCharArray());
        }
        catch (Exception e)
        {

        }
        finally
        {
            inputStream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
                .build();

        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(socketFactory)
                .build();

        return httpClient;
    }

    private String getBasicAuthHeader(){

        return Base64.encodeBase64String((userName + ":" + password).getBytes());
    }
}
