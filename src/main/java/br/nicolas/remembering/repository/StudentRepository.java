package br.nicolas.remembering.repository;

import br.nicolas.remembering.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail (String email);

    @Query ("SELECT s.studentClass.teacher.id FROM Student s WHERE s.id = :studentId")
    Long findTeacherIdByStudentId (@Param("studentId") Long studentId);

    boolean existsByEmail (String email);

    @EntityGraph (attributePaths = {"studentClass"})
    @Query ("SELECT s FROM Student s WHERE s.isPassed = true AND s.studentClass.id = :classId")
    Page<Student> findPassedStudentsByClassId (@Param("classId") Long classId, Pageable pageable);

    @Modifying
    @Query ("UPDATE Student s SET s.studentClass = null WHERE s.studentClass.id = :classId")
    void removeClassesFromStudents (@Param(value = "classId") Long classId);
}
