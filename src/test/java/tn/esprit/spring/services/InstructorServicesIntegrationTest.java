package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class InstructorServicesIntegrationTest {

    @Autowired
    private IInstructorRepository instructorRepository;

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private InstructorServicesImpl instructorServices;

    private Instructor instructor;

    @BeforeEach
    void setUp() {
        // Préparez un instructeur avant chaque test
        instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.of(2020, 1, 1));
    }

    @Test
    void testAddInstructor() {
        // Test de l'ajout d'un instructeur
        Instructor savedInstructor = instructorServices.addInstructor(instructor);
        assertNotNull(savedInstructor);
        assertNotNull(savedInstructor.getNumInstructor());
        assertEquals("John", savedInstructor.getFirstName());
    }

    @Test
    void testRetrieveAllInstructors() {
        // Test pour vérifier la récupération de tous les instructeurs
        instructorServices.addInstructor(instructor);
        List<Instructor> instructors = instructorServices.retrieveAllInstructors();
        assertFalse(instructors.isEmpty());
    }

    @Test
    void testRetrieveInstructor() {
        // Test pour récupérer un instructeur spécifique par son id
        Instructor savedInstructor = instructorServices.addInstructor(instructor);
        Instructor foundInstructor = instructorServices.retrieveInstructor(savedInstructor.getNumInstructor());
        assertNotNull(foundInstructor);
        assertEquals(savedInstructor.getNumInstructor(), foundInstructor.getNumInstructor());
    }

    @Test
    void testUpdateInstructor() {
        // Test de la mise à jour d'un instructeur
        Instructor savedInstructor = instructorServices.addInstructor(instructor);
        savedInstructor.setFirstName("UpdatedName");
        Instructor updatedInstructor = instructorServices.updateInstructor(savedInstructor);
        assertNotNull(updatedInstructor);
        assertEquals("UpdatedName", updatedInstructor.getFirstName());
    }

    @Test
    void testAddInstructorAndAssignToCourse() {
        // Test de l'ajout d'un instructeur et de son affectation à un cours
        Instructor savedInstructor = instructorServices.addInstructor(instructor);

        // Supposez qu'il existe un cours avec un id 1
        Long courseId = 1L;
        Instructor instructorWithCourse = instructorServices.addInstructorAndAssignToCourse(savedInstructor, courseId);
        assertNotNull(instructorWithCourse);
        assertTrue(instructorWithCourse.getCourses().size() > 0);
    }
}

