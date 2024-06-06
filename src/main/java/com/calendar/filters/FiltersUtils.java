package com.calendar.filters;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FiltersUtils {
    public static ServletResponse createRejectResponse(ServletResponse servletResponse) throws IOException {
        var httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.sendError(HttpServletResponse.SC_ACCEPTED);
        httpResponse.setStatus(401);
        return httpResponse;
    }
}
