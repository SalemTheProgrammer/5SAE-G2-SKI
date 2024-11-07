package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;
import javax.persistence.EntityNotFoundException;


import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CourseServicesImpl implements  ICourseServices{

    private ICourseRepository courseRepository;
    private IRegistrationRepository registrationRepository;

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

    @Override
    public Course assignCourseToUser(Long idCourse, Long numRegistration) {
        // Recherche du cours
        Course course = courseRepository.findById(idCourse).orElse(null);
        if (course == null) {
            System.out.println("Le cours avec l'ID " + idCourse + " est introuvable dans la base de données.");
            throw new IllegalArgumentException("Le cours avec l'ID " + idCourse + " n'existe pas.");
        }

        // Recherche de l'inscription
        Registration registration = registrationRepository.findById(numRegistration).orElse(null);
        if (registration == null) {
            System.out.println("L'inscription avec le numéro " + numRegistration + " est introuvable dans la base de données.");
            throw new IllegalArgumentException("L'inscription avec le numéro " + numRegistration + " n'existe pas.");
        }

        // Ajoute le cours à la liste des cours dans l'inscription et sauvegarde
        registration.getCourse().add(course);
        registrationRepository.save(registration);

        return course;
    }


    // Méthode pour rechercher par niveau et trier par prix
    public List<Course> findCoursesByLevelSortedByPrice(int level) {
        return courseRepository.findByLevelOrderByPriceAsc(level);
    }


    public List<Course> findCoursesByTypeAndMinPrice(String typeCourse, double minPrice) {
        return courseRepository.findByTypeCourseAndPriceGreaterThan(typeCourse, minPrice);
    }




}
