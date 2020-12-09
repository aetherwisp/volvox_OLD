package com.github.aetherwisp.volvox.application.index;

import static com.github.aetherwisp.volvox.application.SessionKeys.Index.Login.ERROR;
import java.util.Locale;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {
    //======================================================================
    // Fields
    private final MessageSourceAccessor messageSource;

    //======================================================================
    // Constructors
    @Autowired
    public IndexController(final MessageSource _messageSource) {
        this.messageSource = new MessageSourceAccessor(_messageSource);
    }

    //======================================================================
    // Methods
    @GetMapping(path = {"/index"}, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView index(@SessionAttribute(ERROR) Optional<String> _msgKey, WebRequest _request, SessionStatus _status,
            Locale _locale) {
        _status.setComplete();
        _request.removeAttribute(ERROR, WebRequest.SCOPE_SESSION);

        return new IndexView().setErrorMessage(_msgKey.isPresent()
                ? this.messageSource.getMessage(_msgKey.get(), _locale)
                : "");
    }

    //======================================================================
    // Classes
    private static class IndexView extends ModelAndView {
        private IndexView() {
            super("index");
        }

        private IndexView setErrorMessage(String _errorMessage) {
            this.addObject("errorMessage", _errorMessage);
            return this;
        }
    }
}
