package com.github.aetherwisp.volvox.application.security.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import com.github.aetherwisp.volvox.domain.user.User;
import com.github.aetherwisp.volvox.domain.user.UserRepository;

/**
 * Class that actually performs authentication. The user information and password are obtained from
 * the received token and the unique authentication process is performed. Roles are also given here.
 */
@Component
public class VolvoxAuthenticationProvider implements AuthenticationProvider {
    //======================================================================
    // Fields
    private final UserRepository userRepository;

    //======================================================================
    // Constructors
    @Autowired
    private VolvoxAuthenticationProvider(final UserRepository _userRepository) {
        this.userRepository = Objects.requireNonNull(_userRepository);
    }

    //======================================================================
    // Methods
    @Override
    public Authentication authenticate(Authentication _authentication) throws AuthenticationException {
        final String username = Optional.ofNullable((String) _authentication.getName())
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("username or password is incorrect."));
        final String password = (String) _authentication.getCredentials();

        //======================================================================
        // FIXME: 認証
        final User user = this.userRepository.finder()
                .filterByName(username)
                .find()
                .stream()
                .findFirst()
                .orElse(null);

        //======================================================================
        // FIXME: 権限付与
        final Collection<GrantedAuthority> authorities = new ArrayList<>();

        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }

    /**
     * 指定されたトークンを処理すべきか否かを返します。 false を返すとフレームワークは別の認証プロバイダーを探そうとするので、これを利用して複数の認証方法を提供することもできます。
     */
    @Override
    public boolean supports(Class<?> _authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(_authentication);
    }

}
