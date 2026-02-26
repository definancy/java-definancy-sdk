package com.definancy.sdk.demo;

import java.io.IOException;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.protocol.HttpContext;

public class LogResponseInterceptor implements HttpResponseInterceptor {
    @Override
    public void process(
            HttpResponse httpResponse, EntityDetails entityDetails, HttpContext httpContext)
            throws HttpException, IOException {}
}
