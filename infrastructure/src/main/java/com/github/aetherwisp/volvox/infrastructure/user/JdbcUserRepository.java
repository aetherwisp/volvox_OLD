package com.github.aetherwisp.volvox.infrastructure.user;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.github.aetherwisp.volvox.domain.user.User;
import com.github.aetherwisp.volvox.domain.user.UserRepository;
import com.github.aetherwisp.volvox.infrastructure.AbstractJdbcFinder;
import com.github.aetherwisp.volvox.infrastructure.Queries;

public class JdbcUserRepository extends NamedParameterJdbcDaoSupport implements UserRepository {

    //======================================================================
    // Fields
    private final ConversionService conversionService;

    //======================================================================
    // Constructors
    @Autowired
    public JdbcUserRepository(final DataSource _dataSource, final ConversionService _conversionService) {
        this.setDataSource(Objects.requireNonNull(_dataSource));
        this.conversionService = Objects.requireNonNull(_conversionService);
    }

    //======================================================================
    // Methods
    @Override
    public UserFinder finder() {
        return new JdbcUserFinder(this.getNamedParameterJdbcTemplate(), this.conversionService);
    }

    //======================================================================
    // Classes
    private static class JdbcUserFinder extends AbstractJdbcFinder<User, Integer> implements UserFinder {
        //======================================================================
        // Fields
        private Integer id;
        private String name;

        //======================================================================
        // Constructors
        private JdbcUserFinder(NamedParameterJdbcTemplate _jdbcTemplate, ConversionService _conversionService) {
            super(_jdbcTemplate, _conversionService);
        }

        //======================================================================
        // Methods
        @Override
        public UserFinder filterById(Integer _id) {
            this.id = Objects.requireNonNull(_id);
            return this;
        }

        @Override
        public UserFinder filterByName(String _name) {
            this.name = Objects.requireNonNull(_name);
            return this;
        }

        @Override
        public User get(Integer _firstId, Integer... _remainingIds) {
            return this.getJdbcTemplate()
                    .query(Queries.query()
                            .append("SELECT id,")
                            .append("       name,")
                            .append("       locked,")
                            .append("       expired_at,")
                            .append("       enabled,")
                            .append("  FROM user")
                            .append(" WHERE id = :id")
                            .toString(),
                            Queries.parameters()
                                    .addValue("id", _firstId),
                            this.getRowMapper(User.UserBuilder.class))
                    .stream()
                    .map(builder -> builder.build())
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public List<User> find() {
            return this.getJdbcTemplate()
                    .query(Queries.query()
                            .append("SELECT id,")
                            .append("       name,")
                            .append("       locked,")
                            .append("       expired_at,")
                            .append("       enabled,")
                            .append("  FROM user")
                            .append(" WHERE TRUE")
                            .append("   AND id = :id", null != this.id)
                            .append("   AND name = :name", null != this.name)
                            .toString(),
                            Queries.parameters()
                                    .addValue("id", this.id, null != this.id)
                                    .addValue("name", this.name, null != this.name),
                            this.getRowMapper(User.UserBuilder.class))
                    .stream()
                    .map(builder -> builder.build())
                    .collect(Collectors.toList());

        }

    }
}
