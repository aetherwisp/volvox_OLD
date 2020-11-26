package com.github.aetherwisp.volvox.application.webmvc;

import static com.github.aetherwisp.volvox.Volvox.Profiles.DEV;
import static java.lang.invoke.MethodHandles.lookup;
import static org.apache.logging.log4j.LogManager.getLogger;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Profile(DEV)
public class RequestLoggingFilter implements Filter {
    private static final Logger LOG = getLogger(lookup().lookupClass());

    @Override
    public void doFilter(ServletRequest _request, ServletResponse _response, FilterChain _chain)
            throws IOException, ServletException {

        try {
            if (_request instanceof HttpServletRequest) {
                final HttpServletRequest req = (HttpServletRequest) _request;
                LOG.debug("----------------------------------------------------------------------");
                LOG.debug("{} {} {}", req.getMethod(), req.getRequestURI(), req.getProtocol());

                LOG.debug("Headers");
                Collections.list(req.getHeaderNames())
                        .stream()
                        .filter(name -> !name.equals("cookie"))
                        .forEach(name -> LOG.debug("  {}: {}", name, req.getHeader(name)));

                LOG.debug("Cookies");
                Arrays.stream(req.getCookies())
                        .forEach(cookie -> LOG.debug("  {}={}", cookie.getName(),
                                cookie.getValue()));

                LOG.debug("Parameters");
                Collections.list(req.getParameterNames())
                        .stream()
                        .forEach(name -> LOG.debug("  {}: {}", name, req.getParameter(name)));
                LOG.debug("----------------------------------------------------------------------");
            }
        } finally {
            _chain.doFilter(_request, _response);
        }
    }

}
