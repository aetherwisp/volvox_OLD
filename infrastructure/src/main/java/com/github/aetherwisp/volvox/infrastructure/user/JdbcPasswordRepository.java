package com.github.aetherwisp.volvox.infrastructure.user;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.lang.NonNull;
import com.github.aetherwisp.volvox.domain.user.Password;
import com.github.aetherwisp.volvox.domain.user.PasswordRepository;
import com.github.aetherwisp.volvox.infrastructure.AbstractJdbcFinder;
import com.github.aetherwisp.volvox.infrastructure.Queries;

public class JdbcPasswordRepository extends NamedParameterJdbcDaoSupport implements PasswordRepository {

    //======================================================================
    // Fields
    private final ConversionService conversionService;

    //======================================================================
    // Constructors
    @Autowired
    public JdbcPasswordRepository(final DataSource _dataSource, final ConversionService _conversionService) {
        this.setDataSource(Objects.requireNonNull(_dataSource));
        this.conversionService = Objects.requireNonNull(_conversionService);
    }

    //======================================================================
    // Methods
    @Override
    public PasswordFinder finder() {
        return new JdbcPasswordFinder(this.getNamedParameterJdbcTemplate(), this.conversionService);
    }

    //======================================================================
    // Classes
    private static class JdbcPasswordFinder extends AbstractJdbcFinder<Password, Integer> implements PasswordFinder {
        //======================================================================
        // Fields
        private Integer id;
        private Integer userId;
        private Boolean enabled;

        //======================================================================
        // Constructors
        protected JdbcPasswordFinder(NamedParameterJdbcTemplate _jdbcTemplate, ConversionService _conversionService) {
            super(_jdbcTemplate, _conversionService);
        }

        //======================================================================
        // Methods
        @Override
        public Password get(Integer _firstId, Integer... _remainingIds) {
            return this.getJdbcTemplate()
                    .query(Queries.query()
                            .append("SELECT id,")
                            .append("       user_id,")
                            .append("       password,")
                            .append("       expired_at,")
                            .append("       enabled")
                            .append("  FROM user_password")
                            .append(" WHERE id = :id")
                            .toString(),
                            Queries.parameters()
                                    .addValue("id", _firstId),
                            this.getRowMapper(Password.PasswordBuilder.class))
                    .stream()
                    .map(builder -> builder.build())
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public List<Password> find() {
            final boolean byId = (null != this.id);
            final boolean byUserId = (null != this.userId);
            final boolean byEnabled = (null != this.enabled);

            return this.getJdbcTemplate()
                    .query(Queries.query()
                            .append("SELECT id,")
                            .append("       user_id,")
                            .append("       password,")
                            .append("       expired_at,")
                            .append("       enabled")
                            .append("  FROM user_password")
                            .append(" WHERE TRUE")
                            .append("   AND id = :id", byId)
                            .append("   AND user_id = :userId", byUserId)
                            .append("   AND enabled = :enabled", byEnabled)
                            .append(" ORDER BY id DESC")
                            .toString(),
                            Queries.parameters()
                                    .addValue("id", this.id, byId)
                                    .addValue("userId", this.userId, byUserId)
                                    .addValue("enabled", this.enabled, byEnabled),
                            this.getRowMapper(Password.PasswordBuilder.class))
                    .stream()
                    .map(builder -> builder.build())
                    .collect(Collectors.toList());
        }

        @Override
        public PasswordFinder filterById(@NonNull Integer _id) {
            this.id = Objects.requireNonNull(_id);
            return this;
        }

        @Override
        public PasswordFinder filterByUserId(@NonNull Integer _userId) {
            this.userId = Objects.requireNonNull(_userId);
            return this;
        }

        @Override
        public PasswordFinder filterByEnabled(boolean _enabled) {
            this.enabled = Boolean.valueOf(_enabled);
            return this;
        }
    }
}
