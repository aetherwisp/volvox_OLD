package com.github.aetherwisp.volvox.application.security.auth;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class VolvoxAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    //======================================================================
    // Fields
    private final String contentType;

    //======================================================================
    // Constants
    public VolvoxAuthenticationSuccessHandler(final String _defaultTargetUrl, final String _contentType) {
        super(_defaultTargetUrl);
        this.contentType = Objects.requireNonNull(_contentType);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest _request, HttpServletResponse _response, Authentication _authentication)
            throws IOException, ServletException {
        super.onAuthenticationSuccess(_request, _response, _authentication);
        _response.setContentType(this.contentType);
    }
}