package it.valueson.TwoDatabaseConnection.db1.repository;

import it.valueson.TwoDatabaseConnection.db1.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
