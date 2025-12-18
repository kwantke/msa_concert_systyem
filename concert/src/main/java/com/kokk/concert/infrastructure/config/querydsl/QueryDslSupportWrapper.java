package com.kokk.concert.infrastructure.config.querydsl;


import com.querydsl.core.dml.DeleteClause;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class QueryDslSupportWrapper {
  private final PathBuilder<?> builder;
  private EntityManager entityManager;
  private Querydsl querydsl;
  private JPAQueryFactory jpaQueryFactory;

  public QueryDslSupportWrapper(Class<?> domainClass) {
    this.builder = (new PathBuilderFactory()).create(domainClass);
  }

  protected EntityManager getEntityManager() {
    return this.entityManager;
  }

  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
    this.querydsl = new Querydsl(entityManager, this.builder);
    this.jpaQueryFactory = new JPAQueryFactory(entityManager);
  }

  protected JPQLQuery<?> from(EntityPath<?>... paths) {
    return this.querydsl.createQuery(paths);
  }

  /** @deprecated */
  @Deprecated
  protected DeleteClause<JPADeleteClause> delete(EntityPath<?> path) {
    return new JPADeleteClause(this.entityManager, path);
  }

  /** @deprecated */
  @Deprecated
  protected UpdateClause<JPAUpdateClause> update(EntityPath<?> path) {
    return new JPAUpdateClause(this.entityManager, path);
  }

  /** @deprecated */
  @Deprecated
  protected Object insert(Object entity) {
    this.entityManager.persist(entity);
    this.entityManager.flush();
    return entity;
  }

  protected <T> PathBuilder<T> getBuilder() {
    return (PathBuilder<T>) this.builder;
  }

  protected Querydsl getQuerydsl() {
    return this.querydsl;
  }

  protected JPAQueryFactory getJpaQueryFactory() {
    return this.jpaQueryFactory;
  }

  protected <T> long getTotalCount(JPAQuery<T> query, long totalCount) {
    if (totalCount == 0L) {
      JPAQuery<?> countQuery = (JPAQuery)query.clone();
      countQuery.getMetadata().clearOrderBy();
      return !countQuery.getMetadata().getGroupBy().isEmpty() ? (long)(Integer)Optional.of(countQuery.fetch().size()).orElse(0) : (Long)Optional.ofNullable((Long)countQuery.select(Wildcard.count).fetchOne()).orElse(0L);
    } else {
      return totalCount;
    }
  }

  protected <T> Page<T> getContents(JPAQuery<T> query, Pageable pageable, long totalCount) {
    query.offset(pageable.getOffset());
    query.limit((long)pageable.getPageSize());
    List<T> content = totalCount > pageable.getOffset() ? query.fetch() : Collections.emptyList();
    return new PageImpl(content, pageable, totalCount);
  }

  protected <T> long getCount(JPAQuery<T> query) {
    return (Long)Optional.ofNullable((Long)((JPAQuery)query.clone()).select(Wildcard.count).fetchOne()).orElse(0L);
  }


  protected LocalDate localDateParser(String stringDate) {
    return LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }

  protected LocalDateTime startDate(LocalDate localDate) {
    return localDate.atStartOfDay();
  }

  protected LocalDateTime endDate(LocalDate localDate) {
    return LocalDateTime.of(localDate, LocalTime.of(23, 59, 59));
  }
}
