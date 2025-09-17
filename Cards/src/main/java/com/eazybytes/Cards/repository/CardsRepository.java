package com.eazybytes.Cards.repository;

import com.eazybytes.Cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepository extends JpaRepository<Cards,Long> {
}
