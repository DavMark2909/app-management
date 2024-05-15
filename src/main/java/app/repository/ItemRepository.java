package app.repository;

import app.items.DbItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<DbItem, Long> {
}
