package com.diagrammnt.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Slf4j
@Component
public class SpaFallbackFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String path = request.getRequestURI();
        String ctx = request.getContextPath();
        String p = path;
        if (ctx != null && !ctx.isEmpty() && p.startsWith(ctx)) p = p.substring(ctx.length());
        if (p.startsWith("/api/") || p.contains(".") || p.startsWith("/actuator")) { chain.doFilter(req, res); return; }
        request.getRequestDispatcher("/index.html").forward(req, res);
    }
}
