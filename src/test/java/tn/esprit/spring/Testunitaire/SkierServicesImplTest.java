package tn.esprit.spring.Testunitaire;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.*;
import tn.esprit.spring.services.SkierServicesImpl;

import java.time.LocalDate;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SkierServicesImplTest {

    @Mock
    private ISkierRepository skierRepository;

    @Mock
    private IPisteRepository pisteRepository;

    @Mock
    private ICourseRepository courseRepository;

    @Mock
    private IRegistrationRepository registrationRepository;

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @InjectMocks
    private SkierServicesImpl skierServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllSkiers_shouldReturnListOfSkiers() {
        List<Skier> skiers = Arrays.asList(new Skier(), new Skier());
        when(skierRepository.findAll()).thenReturn(skiers);

        List<Skier> result = skierServices.retrieveAllSkiers();

        assertEquals(2, result.size());
        verify(skierRepository, times(1)).findAll();
    }

    @Test
    void addSkier_shouldSetEndDateBasedOnSubscriptionType() {
        Skier skier = new Skier();
        Subscription subscription = new Subscription();
        subscription.setStartDate(LocalDate.now());
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        skier.setSubscription(subscription);

        when(skierRepository.save(skier)).thenReturn(skier);

        Skier result = skierServices.addSkier(skier);

        assertEquals(LocalDate.now().plusYears(1), result.getSubscription().getEndDate());
        verify(skierRepository, times(1)).save(skier);
    }

    @Test
    void assignSkierToSubscription_shouldAssignSubscriptionToSkier() {
        Skier skier = new Skier();
        Subscription subscription = new Subscription();

        when(skierRepository.findById(1L)).thenReturn(Optional.of(skier));
        when(subscriptionRepository.findById(1L)).thenReturn(Optional.of(subscription));
        when(skierRepository.save(skier)).thenReturn(skier);

        Skier result = skierServices.assignSkierToSubscription(1L, 1L);

        assertEquals(subscription, result.getSubscription());
        verify(skierRepository, times(1)).save(skier);
    }

    @Test
    void addSkierAndAssignToCourse_shouldAssignCourseToRegistrations() {
        Skier skier = new Skier();
        skier.setRegistrations(new HashSet<>());
        Course course = new Course();
        Registration registration = new Registration();
        skier.getRegistrations().add(registration);

        when(skierRepository.save(skier)).thenReturn(skier);
        when(courseRepository.getById(1L)).thenReturn(course);

        Skier result = skierServices.addSkierAndAssignToCourse(skier, 1L);

        for (Registration reg : result.getRegistrations()) {
            assertEquals(course, reg.getCourse());
            assertEquals(skier, reg.getSkier());
        }
        verify(registrationRepository, times(1)).save(registration);
    }

    @Test
    void removeSkier_shouldDeleteSkierById() {
        skierServices.removeSkier(1L);
        verify(skierRepository, times(1)).deleteById(1L);
    }

    @Test
    void retrieveSkier_shouldReturnSkierById() {
        Skier skier = new Skier();
        when(skierRepository.findById(1L)).thenReturn(Optional.of(skier));

        Skier result = skierServices.retrieveSkier(1L);

        assertEquals(skier, result);
        verify(skierRepository, times(1)).findById(1L);
    }

    @Test
    void assignSkierToPiste_shouldAddPisteToSkier() {
        Skier skier = new Skier();
        skier.setPistes(new HashSet<>());
        Piste piste = new Piste();

        when(skierRepository.findById(1L)).thenReturn(Optional.of(skier));
        when(pisteRepository.findById(1L)).thenReturn(Optional.of(piste));
        when(skierRepository.save(skier)).thenReturn(skier);

        Skier result = skierServices.assignSkierToPiste(1L, 1L);

        assertTrue(result.getPistes().contains(piste));
        verify(skierRepository, times(1)).save(skier);
    }

    @Test
    void retrieveSkiersBySubscriptionType_shouldReturnFilteredSkiers() {
        List<Skier> skiers = Arrays.asList(new Skier(), new Skier());
        when(skierRepository.findBySubscription_TypeSub(TypeSubscription.ANNUAL)).thenReturn(skiers);

        List<Skier> result = skierServices.retrieveSkiersBySubscriptionType(TypeSubscription.ANNUAL);

        assertEquals(2, result.size());
        verify(skierRepository, times(1)).findBySubscription_TypeSub(TypeSubscription.ANNUAL);
    }
}
