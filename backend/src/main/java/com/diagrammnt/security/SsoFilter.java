package com.diagrammnt.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class SsoFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.endsWith(".html") || uri.endsWith(".ico") || uri.endsWith(".js") || uri.endsWith(".css") || uri.endsWith(".svg") || uri.endsWith(".png") || uri.endsWith(".jpg") || uri.endsWith(".woff") || uri.endsWith(".ttf") || uri.startsWith("/assets/") || uri.startsWith("/ws/")) { chain.doFilter(request, response); return; }
        if (uri.startsWith("/quotation/")) { chain.doFilter(request, response); return; }
        String token = getTokenFromCookie(request, "SSO_TOKEN");
        if (!StringUtils.hasText(token)) token = request.getParameter("sso_token");
        if (StringUtils.hasText(token) && SsoUtil.validateToken(token)) {
            Claims claims = SsoUtil.parseToken(token);
            request.setAttribute("sso_userId", SsoUtil.getUserId(token));
            request.setAttribute("sso_username", claims.getSubject());
            request.setAttribute("sso_displayName", SsoUtil.getDisplayName(token));
            request.setAttribute("sso_role", SsoUtil.getRole(token));
            chain.doFilter(request, response);
            return;
        }
        if (uri.startsWith("/api/") || "XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) { chain.doFilter(request, response); return; }
        String loginUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/quotation/login?redirect=" + URLEncoder.encode(request.getRequestURL().toString(), StandardCharsets.UTF_8);
        response.sendRedirect(loginUrl);
    }
    private String getTokenFromCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) for (Cookie c : cookies) if (name.equals(c.getName())) return c.getValue();
        return null;
    }
}
