package tn.esprit.spring.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;
import tn.esprit.spring.services.SubscriptionServicesImpl;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubscriptionServicesImplTest {

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @Mock
    private ISkierRepository skierRepository;

    @InjectMocks
    private SubscriptionServicesImpl subscriptionServices;

    private Subscription subscription;
    private Skier skier;

    @BeforeEach
    void setUp() {
        // Initialize mocks and inject them into SubscriptionServicesImpl
        MockitoAnnotations.openMocks(this);

        // Set up test data
        subscription = new Subscription();
        subscription.setNumSub(1L);
        subscription.setTypeSub(TypeSubscription.MONTHLY);
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(subscription.getStartDate().plusMonths(1));

        skier = new Skier();
        skier.setFirstName("John");
        skier.setLastName("Doe");
        skier.setDateOfBirth(LocalDate.of(1990, 1, 1));
        skier.setCity("New York");
        skier.setDateOfBirth(LocalDate.of(1990, 1, 1));
        skier.setSubscription(subscription);
    }

    @Test
    void addSubscription() {
        // Arrange
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(subscription);

        // Act
        Subscription result = subscriptionServices.addSubscription(subscription);

        // Assert
        assertNotNull(result);
        assertEquals(subscription.getStartDate().plusMonths(1), result.getEndDate());
        verify(subscriptionRepository, times(1)).save(subscription);
    }

    @Test
    void updateSubscription() {
        // Arrange
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(subscription);

        // Act
        Subscription result = subscriptionServices.updateSubscription(subscription);

        // Assert
        assertNotNull(result);
        assertEquals(subscription, result);
        verify(subscriptionRepository, times(1)).save(subscription);
    }

    @Test
    void retrieveSubscriptionById() {
        // Arrange
        when(subscriptionRepository.findById(1L)).thenReturn(Optional.of(subscription));

        // Act
        Subscription result = subscriptionServices.retrieveSubscriptionById(1L);

        assertNotNull(result);
        assertEquals(subscription, result);
        verify(subscriptionRepository, times(1)).findById(1L);
    }

    @Test
    void getSubscriptionByType() {
        // Arrange
        Set<Subscription> subscriptions = new HashSet<>();
        subscriptions.add(subscription);
        when(subscriptionRepository.findByTypeSubOrderByStartDateAsc(TypeSubscription.MONTHLY))
                .thenReturn(subscriptions);

        // Act
        Set<Subscription> result = subscriptionServices.getSubscriptionByType(TypeSubscription.MONTHLY);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(subscriptionRepository, times(1))
                .findByTypeSubOrderByStartDateAsc(TypeSubscription.MONTHLY);
    }

    @Test
    void retrieveSubscriptionsByDates() {
        // Arrange
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(subscription);
        LocalDate startDate = LocalDate.now().minusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(1);
        when(subscriptionRepository.getSubscriptionsByStartDateBetween(startDate, endDate))
                .thenReturn(subscriptions);

        // Act
        List<Subscription> result = subscriptionServices.retrieveSubscriptionsByDates(startDate, endDate);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(subscriptionRepository, times(1))
                .getSubscriptionsByStartDateBetween(startDate, endDate);
    }

    @Test
    void retrieveSubscriptions() {
        // Arrange
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(subscription);
        when(subscriptionRepository.findDistinctOrderByEndDateAsc()).thenReturn(subscriptions);
        when(skierRepository.findBySubscription(subscription)).thenReturn(skier);

        // Act
        subscriptionServices.retrieveSubscriptions();

        // Assert
        verify(subscriptionRepository, times(1)).findDistinctOrderByEndDateAsc();
        verify(skierRepository, times(1)).findBySubscription(subscription);
    }

    @Test
    void showMonthlyRecurringRevenue() {
        // Arrange
        when(subscriptionRepository.recurringRevenueByTypeSubEquals(TypeSubscription.MONTHLY))
                .thenReturn(1000f);
        when(subscriptionRepository.recurringRevenueByTypeSubEquals(TypeSubscription.SEMESTRIEL))
                .thenReturn(6000f);
        when(subscriptionRepository.recurringRevenueByTypeSubEquals(TypeSubscription.ANNUAL))
                .thenReturn(12000f);

        // Act
        subscriptionServices.showMonthlyRecurringRevenue();

        // Assert
        verify(subscriptionRepository, times(1))
                .recurringRevenueByTypeSubEquals(TypeSubscription.MONTHLY);
        verify(subscriptionRepository, times(1))
                .recurringRevenueByTypeSubEquals(TypeSubscription.SEMESTRIEL);
        verify(subscriptionRepository, times(1))
                .recurringRevenueByTypeSubEquals(TypeSubscription.ANNUAL);
    }
}
