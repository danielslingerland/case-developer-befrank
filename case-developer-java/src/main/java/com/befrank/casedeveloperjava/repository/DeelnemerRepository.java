package com.befrank.casedeveloperjava.repository;

import com.befrank.casedeveloperjava.repository.entities.Deelnemer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeelnemerRepository extends CrudRepository<Deelnemer, Integer> {
}
