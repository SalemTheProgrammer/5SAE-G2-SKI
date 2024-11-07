package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class InstructorServicesJUnitTest {

    @InjectMocks
    private InstructorServicesImpl instructorServices; // Automatically injects mocks into this service

    @Mock
    private IInstructorRepository instructorRepository; // Mocked repository

    @Mock
    private ICourseRepository courseRepository; // Mocked repository

    @BeforeEach
    void setUp() {
        // Initialize the mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addInstructor() {
        Instructor instructor = new Instructor();
        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor); // Mock save method

        Instructor savedInstructor = instructorServices.addInstructor(instructor);
        assertNotNull(savedInstructor);
        verify(instructorRepository, times(1)).save(instructor); // Verify that the save method was called once
    }

    @Test
    void retrieveAllInstructors() {
        List<Instructor> instructors = List.of(new Instructor(), new Instructor());
        when(instructorRepository.findAll()).thenReturn(instructors); // Mock findAll method

        List<Instructor> result = instructorServices.retrieveAllInstructors();
        assertNotNull(result);
        assertEquals(2, result.size()); // Verify the list contains 2 instructors
    }

    @Test
    void updateInstructor() {
        Instructor instructor = new Instructor();
        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor); // Mock save method

        Instructor updatedInstructor = instructorServices.updateInstructor(instructor);
        assertNotNull(updatedInstructor);
    }

    @Test
    void retrieveInstructor() {
        Instructor instructor = new Instructor();
        when(instructorRepository.findById(1L)).thenReturn(java.util.Optional.of(instructor)); // Mock findById method

        Instructor foundInstructor = instructorServices.retrieveInstructor(1L);
        assertNotNull(foundInstructor);
    }
}
