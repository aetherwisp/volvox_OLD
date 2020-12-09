package com.github.aetherwisp.volvox.presentation.lib.smartclient;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;
import com.github.aetherwisp.volvox.presentation.lib.smartclient.SmartClient.Skin;

@Component
public class SmartClientSkinFilter implements Filter {
    //======================================================================
    // Fields
    private static final String COOKIE_NAME = "VOLVOX-SMARTCLIENT-SKIN";

    //======================================================================
    // Methods
    @Override
    public void doFilter(ServletRequest _request, ServletResponse _response, FilterChain _chain) throws IOException, ServletException {
        if (_request instanceof HttpServletRequest && _response instanceof HttpServletResponse) {
            this.doInternal((HttpServletRequest) _request, (HttpServletResponse) _response);
        }

        _chain.doFilter(_request, _response);
    }

    private void doInternal(HttpServletRequest _request, HttpServletResponse _response) throws IOException, ServletException {
        final Cookie cookie = Optional.ofNullable(WebUtils.getCookie(_request, COOKIE_NAME))
            .orElseGet(() -> {
                final Cookie newCookie = new Cookie(COOKIE_NAME, Skin.GRAPHITE.getLabel());
                newCookie.setSecure(_request.isSecure());
                newCookie.setPath(Optional.of(_request.getContextPath())
                    .filter(path -> path.length() > 0)
                    .orElse("/"));
                newCookie.setMaxAge(Long.valueOf(ChronoUnit.YEARS.getDuration()
                    .getSeconds())
                    .intValue());
                newCookie.setHttpOnly(true);
                return newCookie;
            });
        _response.addCookie(cookie);
        _request.setAttribute(Skin.PARAMETER_NAME, cookie.getValue());
    }
}
