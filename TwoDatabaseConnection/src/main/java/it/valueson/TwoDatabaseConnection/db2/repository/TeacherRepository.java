package it.valueson.TwoDatabaseConnection.db2.repository;

import it.valueson.TwoDatabaseConnection.db2.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
