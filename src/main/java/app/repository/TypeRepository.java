package app.repository;

import app.items.TypeUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<TypeUnit, Integer> {
    TypeUnit findById(int id);

    TypeUnit findByName(String name);
}
