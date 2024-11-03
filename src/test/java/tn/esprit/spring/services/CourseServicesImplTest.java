package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)

public class CourseServicesImplTest {

    @InjectMocks
    private CourseServicesImpl courseServices;

    @Mock
    private ICourseRepository courseRepository;


    @Mock
    private ISubscriptionRepository subscriptionRepository;

    private Course course;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        course = new Course();
        course.setIdCourse(1L);
        course.setLevel(2);
        course.setTypeCourse(TypeCourse.COLLECTIVE_ADULT); // Use an actual type from your TypeCourse enum
        course.setSupport(Support.SKI); // Use an actual support from your Support enum
        course.setPrice(100.0f);
        course.setTimeSlot(5);
    }

    @Test
    void retrieveAllCourses() {
        // Arrange
        List<Course> courses = new ArrayList<>();
        courses.add(course);
        when(courseRepository.findAll()).thenReturn(courses);

        // Act
        List<Course> result = courseServices.retrieveAllCourses();

        // Assert
        assertEquals(1, result.size());
        assertEquals(course, result.get(0));
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    void findCoursesByLevelTypeSupport() {
        // Arrange
        int level = 3;
        TypeCourse typeCourse = TypeCourse.INDIVIDUAL;  // Remplacez par la valeur appropriée de votre enum
        Support support = Support.SKI;            // Remplacez par la valeur appropriée de votre enum

        List<Course> courses = new ArrayList<>();
        courses.add(course);

        // Simuler le comportement du repository
        when(courseRepository.findByLevelAndTypeCourseAndSupport(level, typeCourse, support)).thenReturn(courses);

        // Act
        List<Course> result = courseServices.findCoursesByLevelTypeSupport(level, typeCourse, support);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(course, result.get(0));
        verify(courseRepository, times(1)).findByLevelAndTypeCourseAndSupport(level, typeCourse, support);
    }

    @Test
    void addCourse() {
        // Arrange
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        // Act
        Course result = courseServices.addCourse(course);

        // Assert
        assertNotNull(result);
        assertEquals(course, result);
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void updateCourse() {
        // Arrange
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        // Act
        Course result = courseServices.updateCourse(course);

        // Assert
        assertNotNull(result);
        assertEquals(course, result);
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void retrieveCourse() {
        // Arrange
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        // Act
        Course result = courseServices.retrieveCourse(1L);

        // Assert
        assertNotNull(result);
        assertEquals(course, result);
        verify(courseRepository, times(1)).findById(1L);
    }


    @Test
    public void testGetTotalRevenue() {
        // Données de test
        Long idCourse = 1L;
        Course course = new Course();
        course.setPrice(200.0f);

        when(courseRepository.findById(idCourse)).thenReturn(Optional.of(course));
        when(subscriptionRepository.countByCourse(course)).thenReturn(10);

        // Exécution du test
        Float totalRevenue = courseServices.calculateTotalRevenue(idCourse);

        // Vérifications
        assertNotNull(totalRevenue);
        assertEquals(2000.0f, totalRevenue);
    }

    @Test
    public void testGetTotalRevenueCourseNotFound() {
        Long idCourse = 1L;

        when(courseRepository.findById(idCourse)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            courseServices.calculateTotalRevenue(idCourse);
        });

        assertEquals("Course not found with id " + idCourse, exception.getMessage());
    }
}
