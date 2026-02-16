package br.nicolas.remembering.repository;

import br.nicolas.remembering.entity.Class;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {

    @EntityGraph (attributePaths = {"teacher"})
    @Query ("SELECT c FROM Class c WHERE UPPER(c.discipline) = UPPER(:discipline)")
    Page<Class> getAllClassesByDiscipline (@Param(value = "discipline") String disciplineStr, Pageable pageable);

    int countByTeacherId (Long teacherId);

    @Modifying
    @Query ("UPDATE Class c SET c.teacher = null WHERE c.teacher.id = :teacherId")
    void removeTeacherFromClasses(@Param(value = "teacherId") Long teacherId);
}
