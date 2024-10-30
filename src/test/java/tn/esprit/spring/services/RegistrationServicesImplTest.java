package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISkierRepository;
import java.util.Optional;
import tn.esprit.spring.entities.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class RegistrationServicesImplTest {

    @InjectMocks
    private RegistrationServicesImpl registrationService;

    @Mock
    private IRegistrationRepository registrationRepository;

    @Mock
    private ISkierRepository skierRepository;

    @Mock
    private ICourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addRegistrationAndAssignToSkier() {
        // Arrange
        Registration registration = new Registration();
        Long skierId = 1L;
        Skier skier = new Skier();
        skier.setNumSkier(skierId);
        when(skierRepository.findById(skierId)).thenReturn(Optional.of(skier));
        when(registrationRepository.save(any(Registration.class))).thenReturn(registration);

        // Act
        Registration result = registrationService.addRegistrationAndAssignToSkier(registration, skierId);

        // Assert
        assertNotNull(result);
        assertEquals(skier, result.getSkier());
        verify(registrationRepository).save(registration);
    }

    @Test
    void assignRegistrationToCourse() {
        // Arrange
        Registration registration = new Registration();
        Long registrationId = 1L;
        Long courseId = 2L;
        Course course = new Course();
        course.setNumCourse(courseId);
        when(registrationRepository.findById(registrationId)).thenReturn(Optional.of(registration));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(registrationRepository.save(any(Registration.class))).thenReturn(registration);

        // Act
        Registration result = registrationService.assignRegistrationToCourse(registrationId, courseId);

        // Assert
        assertNotNull(result);
        assertEquals(course, result.getCourse());
        verify(registrationRepository).save(registration);
    }


}