package ru.torgcrm.crawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.torgcrm.crawler.domain.Value;

@Repository
public interface ValueRepository extends JpaRepository<Value, Long> {
}
