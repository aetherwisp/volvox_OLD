package com.github.aetherwisp.volvox.domain;

public interface Entity<E extends Entity<E>> {

    /**
     * @param other 比較するエンティティ
     * @return このエンティティが指定されたエンティティと同一（等価とは限らない）であるなら true、そうでないなら false
     */
    boolean sameAs(E other);
}
