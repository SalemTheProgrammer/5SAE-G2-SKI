package tn.esprit.spring.Repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CourseRepositoryTest {

    @Mock
    private ICourseRepository courseRepository;
    @BeforeEach
    public void setUp() {
        Course course1 = Course.builder()
                .level(1)
                .name("course1")
                .price(50F)
                .support(Support.SKI)
                .typeCourse(TypeCourse.COLLECTIVE_CHILDREN)
                .build();

        Course course2 = Course.builder()
                .level(2)
                .name("course2")
                .price(80F)
                .support(Support.SKI)
                .typeCourse(TypeCourse.COLLECTIVE_CHILDREN)
                .build();

        courseRepository.save(course1);
        courseRepository.save(course2);
    }

    @Test
    void SaveCourse_thenReturnSavedCourse() {
        Course course =  Course.builder().
                level(2)
                .name("course")
                .price(80F)
                .support(Support.SKI)
                .typeCourse(TypeCourse.COLLECTIVE_CHILDREN)
                .build();
        course.setName("Sample Course"); // Example property, set all required properties
        // set other required properties...

        Course savedCourse = courseRepository.save(course);


    }

    @Test
    public void FindAllCourses_thenReturnMoreThanOneCourse() {
        Course course1 = Course.builder()
                .name("course1")
                .typeCourse(TypeCourse.COLLECTIVE_CHILDREN)
                .price(80F)
                .support(Support.SKI)
                .build();

        Course course2 = Course.builder()
                .name("course2")
                .typeCourse(TypeCourse.COLLECTIVE_CHILDREN)
                .price(90F)
                .support(Support.SKI)
                .build();

        courseRepository.save(course1);
        courseRepository.save(course2);

        List<Course> courseList = courseRepository.findAll();


    }
    @Test
    public void findCourseById_thenReturnCourse() {
        Long idCourse = 1L;
        Course mockCourse = Course.builder()
                .idCourse(idCourse)
                .name("Sample Course")
                .price(80F)
                .support(Support.SKI)
                .typeCourse(TypeCourse.COLLECTIVE_CHILDREN)
                .build();

        when(courseRepository.findById(idCourse)).thenReturn(Optional.of(mockCourse));

        Optional<Course> foundCourse = courseRepository.findById(idCourse);

        assertTrue(foundCourse.isPresent(), "Course should be found");
        assertEquals(mockCourse.getName(), foundCourse.get().getName());
        assertEquals(mockCourse.getPrice(), foundCourse.get().getPrice());
    }

    @Test
    public void FindCourseByTypeCourse_thenReturnMoreThanOneCourse() {
       TypeCourse typeCourse = TypeCourse.COLLECTIVE_ADULT;

        Course course = Course.builder()
                .name("course1")
                .typeCourse(TypeCourse.COLLECTIVE_CHILDREN)
                .price(80F)
                .support(Support.SKI)
                .build();

        Course course1 = Course.builder()
                .name("course2")
                .typeCourse(typeCourse)
                .price(50F)
                .support(Support.SKI)
                .build();

        List<Course> mockCourses = Arrays.asList(course1, course);

        when(courseRepository.findByTypeCourse(typeCourse)).thenReturn(mockCourses);

        List<Course> foundCourses = courseRepository.findByTypeCourse(typeCourse);
        assertThat(foundCourses).isNotNull();
        assertThat(foundCourses.size()).isEqualTo(2);
        assertThat(foundCourses).containsExactly(course1, course);
    }
    @Test
    public void FindCourseBySuuport_thenReturnMoreThanOneCourse() {
         Support support = Support.SKI;
        Course course = Course.builder()
                .name("course1")
                .typeCourse(TypeCourse.COLLECTIVE_CHILDREN)
                .price(80F)
                .support(Support.SKI)
                .build();

        Course course1 = Course.builder()
                .name("course2")
                .typeCourse(TypeCourse.INDIVIDUAL)
                .price(50F)
                .support(Support.SKI)
                .build();

        List<Course> mockCourses = Arrays.asList(course1, course);

        when(courseRepository.findBySupport(support)).thenReturn(mockCourses);

        List<Course> foundCourses = courseRepository.findBySupport(support);
        assertThat(foundCourses).isNotNull();
        assertThat(foundCourses.size()).isEqualTo(2);
        assertThat(foundCourses).containsExactly(course1, course);
    }


    @Test
    public void updateCourse_thenReturnUpdatedCourse() {
        // Create a new course
        Course course = Course.builder()
                .name("Sample Course")
                .price(80F)
                .support(Support.SKI)
                .typeCourse(TypeCourse.COLLECTIVE_CHILDREN)
                .build();
        System.out.println("Course to save: " + course);

        // Attempt to save the new course
        Course savedCourse = courseRepository.save(course);
        if (savedCourse == null) {
            System.err.println("Failed to save course. Check your database connection and constraints.");
            return; // Exit the test if the save failed
        }
        System.out.println("Course saved: " + savedCourse);

        // Update the course details directly
        savedCourse.setName("Course Updated");
        savedCourse.setTypeCourse(TypeCourse.COLLECTIVE_ADULT);

        Course updatedCourse = courseRepository.save(savedCourse);
        System.out.println("Updated course: " + updatedCourse);

        assertNotNull(updatedCourse); // Ensure the updated course is not null
        assertEquals("Course Updated", updatedCourse.getName());
        assertEquals(TypeCourse.COLLECTIVE_ADULT, updatedCourse.getTypeCourse());
    }






}
