package com.kokk.concert.infrastructure.db.custom;


import com.kokk.concert.domain.model.entity.ConcertSessionSeat;
import com.kokk.concert.domain.model.entity.QConcertSessionSeat;
import com.kokk.concert.domain.model.entity.QSeat;
import com.kokk.concert.domain.model.valueObject.CustomConcertSessionSeat;
import com.kokk.concert.infrastructure.config.querydsl.QueryDslSupportWrapper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Repository
@Slf4j
public class CustomConcertSessionSeatRepositoryImpl extends QueryDslSupportWrapper implements CustomConcertSessionSeatRepository {

  //private final JPAQueryFactory queryFactory;
  @PersistenceContext
  public void setEntityManager(EntityManager entityManager) {
    super.setEntityManager(entityManager);
  }


  public CustomConcertSessionSeatRepositoryImpl() {
    super(ConcertSessionSeat.class);
  }

  @Override
  public List<CustomConcertSessionSeat> findConcertSessionSeatsByConcertSessionId(Long concertSessionId, Sort sort) {
    QConcertSessionSeat ccs = QConcertSessionSeat.concertSessionSeat;
    QSeat seat = QSeat.seat;

    BooleanBuilder builder = new BooleanBuilder();
    builder.and(ccs.concertSessionId.eq(concertSessionId));

    JPAQuery<CustomConcertSessionSeat> query = getJpaQueryFactory()
            .select(Projections.bean(
                    CustomConcertSessionSeat.class,
                    ccs.id,
                    ccs.seat.id.as("seatId"),
                    ccs.concertSessionId,
                    ccs.reserved,
                    seat.seatRow,
                    seat.seatColumn

            ))
            .from(ccs)
            .innerJoin(ccs.seat, seat)
            .where(builder)
      ;
    toOrderSpecifiers(query, sort, ccs, seat);

    return query.fetch();
  }

  private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
          "id", "seatId", "concertSessionId", "reserved", "seatRow", "seatColumn"
  );

  private void toOrderSpecifiers(JPQLQuery<CustomConcertSessionSeat> query, Sort sort, QConcertSessionSeat css, QSeat seat) {
    if (sort == null || sort.isUnsorted()) {
      query.orderBy(css.id.asc());
    }

    List<OrderSpecifier<?>> specs = new ArrayList<>();
    for (Sort.Order o : sort) {
      String p = o.getProperty();
      if (!ALLOWED_SORT_FIELDS.contains(p)) continue;

      boolean asc = o.isAscending();

      switch (p) {
        // ConcertSessionSeat 쪽
        case "id" -> specs.add(asc ? css.id.asc() : css.id.desc());
        case "seatId" -> specs.add(asc ? css.seat.id.asc() : css.seat.id.desc());
        case "concertSessionId" -> specs.add(asc ? css.concertSessionId.asc() : css.concertSessionId.desc());
        case "reserved" -> specs.add(asc ? css.reserved.asc() : css.reserved.desc());

        // Seat 쪽
        case "seatRow" -> specs.add(asc ? seat.seatRow.asc() : seat.seatRow.desc());
        case "seatColumn" -> specs.add(asc ? seat.seatColumn.asc() : seat.seatColumn.desc());
      }
    }
    query.orderBy(specs.toArray(OrderSpecifier[]::new));
  }
}
