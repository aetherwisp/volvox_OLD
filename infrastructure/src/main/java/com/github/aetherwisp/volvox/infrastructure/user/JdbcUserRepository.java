package com.github.aetherwisp.volvox.infrastructure.user;

import java.sql.Types;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.github.aetherwisp.volvox.domain.user.Password;
import com.github.aetherwisp.volvox.domain.user.PasswordRepository;
import com.github.aetherwisp.volvox.domain.user.PasswordRepository.PasswordFinder;
import com.github.aetherwisp.volvox.domain.user.User;
import com.github.aetherwisp.volvox.domain.user.UserRepository;
import com.github.aetherwisp.volvox.infrastructure.AbstractJdbcFinder;
import com.github.aetherwisp.volvox.infrastructure.Queries;

@Component
public class JdbcUserRepository extends NamedParameterJdbcDaoSupport implements UserRepository {

    //======================================================================
    // Fields
    private final ConversionService conversionService;

    private final PasswordRepository passwordRepository;

    //======================================================================
    // Constructors
    @Autowired
    public JdbcUserRepository(final DataSource _dataSource, final ConversionService _conversionService, final PasswordRepository _passwordRepository) {
        this.setDataSource(Objects.requireNonNull(_dataSource));
        this.conversionService = Objects.requireNonNull(_conversionService);
        this.passwordRepository = Objects.requireNonNull(_passwordRepository);
    }

    //======================================================================
    // Methods
    @Override
    public UserFinder finder() {
        return new JdbcUserFinder(this.getNamedParameterJdbcTemplate(), this.conversionService, this.passwordRepository.finder());
    }

    //======================================================================
    // Classes
    private static class JdbcUserFinder extends AbstractJdbcFinder<User, Integer> implements UserFinder {
        //======================================================================
        // Fields
        private final PasswordFinder passwordFinder;
        private Integer id;
        private String name;

        //======================================================================
        // Constructors
        private JdbcUserFinder(NamedParameterJdbcTemplate _jdbcTemplate, ConversionService _conversionService, PasswordFinder _passwordFinder) {
            super(_jdbcTemplate, _conversionService);
            this.passwordFinder = Objects.requireNonNull(_passwordFinder);
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
            final Password password = this.passwordFinder.filterByUserId(_firstId)
                    .filterByEnabled(true)
                    .find()
                    .stream()
                    .findFirst()
                    .get();

            if (null == password) {
                return null;
            } else {
                return this.getJdbcTemplate()
                        .query(Queries.query()
                                .append("SELECT id,")
                                .append("       name,")
                                .append("       locked,")
                                .append("       expired_at,")
                                .append("       enabled")
                                .append("  FROM \"user\"")
                                .append(" WHERE id = :id")
                                .toString()
                                .replaceAll(" +", " "),
                                Queries.parameters()
                                        .addValue("id", _firstId, Types.INTEGER),
                                this.getRowMapper(User.UserBuilder.class))
                        .stream()
                        .map(builder -> builder.setPassword(password))
                        .map(builder -> builder.build())
                        .findFirst()
                        .orElse(null);
            }
        }

        @Override
        public List<User> find() {
            final boolean byId = (null != this.id);
            final boolean byName = (null != this.name);
            return this.getJdbcTemplate()
                    .query(Queries.query()
                            .append("SELECT id,")
                            .append("       name,")
                            .append("       locked,")
                            .append("       expired_at,")
                            .append("       enabled")
                            .append("  FROM \"user\"")
                            .append(" WHERE TRUE")
                            .append("   AND id = :id", byId)
                            .append("   AND name = :name", byName)
                            .toString()
                            .replaceAll(" +", " "),
                            Queries.parameters()
                                    .addValue("id", this.id, Types.INTEGER, byId)
                                    .addValue("name", this.name, Types.VARCHAR, byName),
                            this.getRowMapper(User.UserBuilder.class))
                    .stream()
                    .map(builder -> builder.build())
                    .collect(Collectors.toList());

        }

    }
}
