package com.github.aetherwisp.volvox.domain.user;

import com.github.aetherwisp.volvox.domain.Repository;
import com.github.aetherwisp.volvox.domain.user.UserRepository.UserFinder;

public interface UserRepository extends Repository<User, Integer, UserFinder> {

    public static interface UserFinder extends Repository.Finder<User, Integer> {

        UserFinder filterById(Integer id);

        UserFinder filterByName(String name);
    }
}
