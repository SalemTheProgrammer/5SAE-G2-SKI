package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.entities.TypeSubscription;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Long> {

    @Transactional
    void deleteByLevelAndTypeCourseAndSupport(int level, TypeCourse typeCourse, Support support);
    List<Course> findByLevelAndTypeCourseAndSupport(int level, TypeCourse typeCourse, Support support);
    List<Course> findByTypeCourse(TypeCourse typeCourse);
    List<Course> findBySupport(Support support);

}
