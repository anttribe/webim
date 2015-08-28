/*
 * 文  件   名: HTTPClientUtils.java
 * 版         本 : (Anttribe).webim-base-infra. All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月3日
 */
package org.anttribe.webim.base.infra.httpclient;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.anttribe.webim.base.infra.httpclient.vo.Credential;
import org.anttribe.webim.base.infra.httpclient.vo.HTTPMethod;
import org.anttribe.webim.base.infra.httpclient.vo.Token;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author zhaoyong
 * @version 2015年8月3日
 */
public class HTTPClientUtils
{
    private static final JsonNodeFactory factory = new JsonNodeFactory(false);
    
    /**
     * Send SSL Request
     * 
     * @return
     */
    public static ObjectNode sendHTTPRequest(URL url, Credential credential, Object data, HTTPMethod httpMethod)
    {
        HttpClient httpClient = getClient(true);
        
        ObjectNode resObjectNode = factory.objectNode();
        try
        {
            HttpResponse response = null;
            
            HttpRequestBase request = null;
            if (HTTPMethod.GET == httpMethod)
            {
                request = new HttpGet(url.toURI());
            }
            else if (HTTPMethod.POST == httpMethod)
            {
                request = new HttpPost(url.toURI());
                ((HttpPost)request).setEntity(new StringEntity(data.toString(), "UTF-8"));
            }
            else if (HTTPMethod.PUT == httpMethod)
            {
                request = new HttpPut(url.toURI());
                ((HttpPut)request).setEntity(new StringEntity(data.toString(), "UTF-8"));
            }
            else if (HTTPMethod.DELETE == httpMethod)
            {
                request = new HttpDelete(url.toURI());
            }
            
            if (credential != null)
            {
                Token.applyAuthentication(request, credential);
            }
            response = httpClient.execute(request);
            
            HttpEntity entity = response.getEntity();
            if (null != entity)
            {
                String responseContent = EntityUtils.toString(entity, "UTF-8");
                EntityUtils.consume(entity);
                
                ObjectMapper mapper = new ObjectMapper();
                JsonFactory factory = mapper.getJsonFactory();
                JsonParser jp = factory.createJsonParser(responseContent);
                
                resObjectNode = mapper.readTree(jp);
                resObjectNode.put("statusCode", response.getStatusLine().getStatusCode());
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            httpClient.getConnectionManager().shutdown();
        }
        
        return resObjectNode;
    }
    
    /**
     * Create a httpClient instance
     * 
     * @param isSSL
     * @return HttpClient instance
     */
    public static HttpClient getClient(boolean isSSL)
    {
        HttpClient httpClient = new DefaultHttpClient();
        if (isSSL)
        {
            X509TrustManager xtm = new X509TrustManager()
            {
                public void checkClientTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException
                {
                }
                
                public void checkServerTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException
                {
                }
                
                public X509Certificate[] getAcceptedIssuers()
                {
                    return null;
                }
            };
            
            try
            {
                SSLContext ctx = SSLContext.getInstance("TLS");
                ctx.init(null, new TrustManager[] {xtm}, null);
                SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
                httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));
            }
            catch (Exception e)
            {
                throw new RuntimeException();
            }
        }
        
        return httpClient;
    }
    
    /**
     * @param schema
     * @param host
     * @param path
     * @return
     */
    public static URL getURL(String schema, String host, String path)
    {
        URL url = null;
        try
        {
            url = new URL(schema, host, "/" + path);
        }
        catch (MalformedURLException e)
        {
        }
        return url;
    }
}