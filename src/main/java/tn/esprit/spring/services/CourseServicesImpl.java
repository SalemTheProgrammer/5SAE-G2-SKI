package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;
import javax.persistence.EntityNotFoundException;


import java.util.List;
@AllArgsConstructor
@Service
public class CourseServicesImpl implements  ICourseServices{

    private ICourseRepository courseRepository;
    @Autowired
    private ISubscriptionRepository subscriptionRepository;
    @Override
    public List<Course> retrieveAllCourses() {
        return courseRepository.findAll();
    }


    @Override
    public List<Course> deleteAllCourses() {
        // Récupérer tous les cours avant suppression
        List<Course> courses = courseRepository.findAll();

        // Supprimer tous les cours
        courseRepository.deleteAll();

        // Retourner la liste des cours supprimés
        return courses;
    }


    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course retrieveCourse(Long idCourse) {

        return courseRepository.findById(idCourse).orElse(null);
    }

    @Override
    public List<Course> findCoursesByLevelTypeSupport(int level, TypeCourse typeCourse, Support support) {
        // Utilisation du repository pour faire la recherche
        return courseRepository.findByLevelAndTypeCourseAndSupport(level, typeCourse, support);
    }
    @Override
    public void deleteCourses(int level, TypeCourse typeCourse, Support support) {
        courseRepository.deleteByLevelAndTypeCourseAndSupport(level, typeCourse, support);
    }


    public Float calculateTotalRevenue(Long idCourse) {
        Course course = courseRepository.findById(idCourse)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id " + idCourse));

        int numberOfSubscriptions = subscriptionRepository.countByCourse(course);

        // Calculer le revenu total
        Float totalRevenue = course.getPrice() * numberOfSubscriptions;

        return totalRevenue;
    }


}
