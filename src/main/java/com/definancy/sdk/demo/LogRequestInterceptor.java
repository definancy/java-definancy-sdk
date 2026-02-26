package com.definancy.sdk.demo;

import java.io.IOException;
import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpRequestInterceptor;
import org.apache.hc.core5.http.protocol.HttpContext;

public class LogRequestInterceptor implements HttpRequestInterceptor {

    @Override
    public void process(
            HttpRequest httpRequest, EntityDetails entityDetails, HttpContext httpContext)
            throws HttpException, IOException {
        //System.out.println(entityDetails.);
    }
}
