package codeOrchestra.colt.core.license.plimus;

import codeOrchestra.colt.core.net.ProxyModel;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

/**
 * @author Alexander Eliseyev
 */
public final class PlimusHelper {

    private static final int SOCKET_READ_TIMEOUT = 1500;
    private static final int CONNECTION_TIMEOUT = 2000;

    private static final String VALIDATION_URL = "https://www.plimus.com/jsp/validateKey.jsp";
    private static final String PRODUCT_ID = "902584";

//  private static final String VALIDATION_URL = "https://sandbox.plimus.com/jsp/validateKey.jsp"; // sandbox
//  private static final String PRODUCT_ID = "294006"; // sandbox

    private static HttpClient httpClient = new HttpClient();

    private PlimusHelper() {
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(CONNECTION_TIMEOUT);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(SOCKET_READ_TIMEOUT);
    }

    public static PlimusResponse registerKey(String key) throws IOException {
        return executePlimusAction(key, PlimusValidationAction.REGISTER);
    }

    public static PlimusResponse validateKey(String key) throws IOException {
        return executePlimusAction(key, PlimusValidationAction.VALIDATE);
    }

    private static PlimusResponse executePlimusAction(String key, PlimusValidationAction action) throws IOException {
        GetMethod getMethod = new GetMethod(VALIDATION_URL);

        getMethod.getParams().setParameter("http.socket.timeout", Integer.valueOf(SOCKET_READ_TIMEOUT));

        getMethod.setQueryString(new NameValuePair[]{
                new NameValuePair("action", action.name()),
                new NameValuePair("productId", PRODUCT_ID),
                new NameValuePair("key", key)
        });

        ProxyModel proxyModel = ProxyModel.getInstance();
        if (proxyModel.getUseProxy()) {
            String host = proxyModel.getHost();
            int port = Integer.parseInt(proxyModel.getPort());
            httpClient.getHostConfiguration().setProxy(host, port);

            Credentials credentials = new UsernamePasswordCredentials(proxyModel.getUsername(), proxyModel.getPassword());
            AuthScope authScope = new AuthScope(host, port);

            httpClient.getState().setProxyCredentials(authScope, credentials);
        } else if(httpClient.getHostConfiguration().getProxyHost() != null) {
            httpClient = new HttpClient();
        }

        httpClient.executeMethod(getMethod);

        return new PlimusResponse(getMethod.getResponseBodyAsString());
    }

}
