package com.github.aetherwisp.volvox.domain.user;

import java.time.LocalDateTime;
import java.util.Objects;
import com.github.aetherwisp.volvox.domain.Builder;

public class Password {

    //======================================================================
    // Fields
    /** 【パスワード ID】パスワードの識別子 */
    private final Integer id;

    /** 【ユーザ ID】パスワードを所有するユーザの ID */
    private final Integer userId;

    /** 【パスワード】パスワード文字列 */
    private final String password;

    /** 【有効期限】パスワードの有効期限 */
    private final LocalDateTime expiredAt;

    /** 【有効】TRUE：有効、FALSE：無効 */
    private final boolean enabled;

    //======================================================================
    // Constructors
    private Password(final PasswordBuilder _builder) {
        final PasswordBuilder builder = Objects.requireNonNull(_builder);
        this.id = Objects.requireNonNull(builder.id);
        this.userId = Objects.requireNonNull(builder.userId);
        this.password = Objects.requireNonNull(builder.password);
        this.expiredAt = Objects.requireNonNull(builder.expiredAt);
        this.enabled = builder.enabled;
    }

    //======================================================================
    // Getters
    public Integer getId() {
        return this.id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public String getPassword() {
        return this.password;
    }

    public LocalDateTime getExpiredAt() {
        return this.expiredAt;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    //======================================================================
    // Classes
    public static class PasswordBuilder implements Builder<Password> {
        private Integer id;
        private Integer userId;
        private String password;
        private LocalDateTime expiredAt;
        private boolean enabled;

        //======================================================================
        // Methods
        @Override
        public Password build() {
            return new Password(this);
        }

        //======================================================================
        // Getters
        public Integer getId() {
            return this.id;
        }

        public Integer getUserId() {
            return this.userId;
        }

        public String getPassword() {
            return this.password;
        }

        public LocalDateTime getExpiredAt() {
            return this.expiredAt;
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        //======================================================================
        // Setters
        public void setId(Integer _id) {
            this.id = _id;
        }

        public void setUserId(Integer _userId) {
            this.userId = _userId;
        }

        public void setPassword(String _password) {
            this.password = _password;
        }

        public void setExpiredAt(LocalDateTime _expiredAt) {
            this.expiredAt = _expiredAt;
        }

        public void setEnabled(boolean _enabled) {
            this.enabled = _enabled;
        }

    }
}
