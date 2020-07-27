package com.github.aetherwisp.volvox.domain.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.github.aetherwisp.volvox.domain.Builder;
import com.github.aetherwisp.volvox.domain.Entity;
import com.github.aetherwisp.volvox.domain.environment.Environments;

public final class User implements Entity<User>, UserDetails {
    private static final long serialVersionUID = 4128472505610227444L;

    //======================================================================
    // Fields
    /** A number that uniquely identifies this user. */
    private final Integer id;

    /** The name of this user. */
    private final String name;

    /** {@code true} if this user is locked, {@code false} otherwise. */
    private final boolean locked;

    /** The expiration UTC date for this user. */
    private final LocalDateTime expiredAt;

    /** {@code true} if this user is valid, {@code false} otherwise. */
    private final boolean enabled;

    private final Password password;

    private List<Permission> permissions;

    //======================================================================
    // Constructors
    private User(final UserBuilder _builder) {
        final UserBuilder builder = Objects.requireNonNull(_builder);
        this.id = Objects.requireNonNull(builder.id);
        this.name = Objects.requireNonNull(builder.name);
        this.locked = builder.locked;
        this.expiredAt = Objects.requireNonNull(builder.expiredAt);
        this.enabled = builder.enabled;
        this.password = Objects.requireNonNull(builder.password);
        this.permissions = Collections.unmodifiableList(new ArrayList<>(builder.permissions));
    }

    //======================================================================
    // Methods
    @Override
    public boolean sameAs(User _other) {
        return null != _other ? this.id.equals(_other.id) : false;
    }

    //======================================================================
    // Getters
    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean isLocked() {
        return this.locked;
    }

    public LocalDateTime getExpiredAt() {
        return this.expiredAt;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getPassword() {
        return this.password.getPassword();
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        // FIXME: 未実装
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        // FIXME: 未実装
        return false;
    }

    /**
     * @return ユーザーの資格情報（パスワード）の有効期限が切れているなら true、そうでないなら false
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return this.password.getExpiredAt()
                .isBefore(Environments.currentLocalDateTime());
    }

    //======================================================================
    // Classes
    public static class UserBuilder implements Builder<User> {
        private Integer id;
        private String name;
        private boolean locked;
        private LocalDateTime expiredAt;
        private boolean enabled;
        private Password password;
        private final List<Permission> permissions = new ArrayList<>();

        @Override
        public User build() {
            return new User(this);
        }

        //======================================================================
        // Getters
        public Integer getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public boolean isLocked() {
            return this.locked;
        }

        public LocalDateTime getExpiredAt() {
            return this.expiredAt;
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        public Password getPassword() {
            return this.password;
        }

        public List<Permission> getPermissions() {
            return this.permissions;
        }

        //======================================================================
        // Setters
        public UserBuilder setId(Integer _id) {
            this.id = _id;
            return this;
        }

        public UserBuilder setName(String _name) {
            this.name = _name;
            return this;
        }

        public UserBuilder setLocked(boolean _locked) {
            this.locked = _locked;
            return this;
        }

        public UserBuilder setExpiredAt(LocalDateTime _expiredAt) {
            this.expiredAt = _expiredAt;
            return this;
        }

        public UserBuilder setEnabled(boolean _enabled) {
            this.enabled = _enabled;
            return this;
        }

        public UserBuilder setPassword(Password _password) {
            this.password = _password;
            return this;
        }

        public UserBuilder setPermissions(List<Permission> _permissions) {
            this.permissions.clear();
            if (null != _permissions) {
                this.permissions.addAll(_permissions);
            }
            return this;
        }
    }
}
