package com.example.gocarrentspringbootapplication.impl.builders;

import com.example.gocarrentspringbootapplication.api.builders.IAnnouncementBuilder;
import com.example.gocarrentspringbootapplication.api.dao.repositories.UserRepository;
import com.example.gocarrentspringbootapplication.impl.models.Announcement;
import com.example.gocarrentspringbootapplication.impl.models.AnnouncementDetails;
import com.example.gocarrentspringbootapplication.impl.models.TestAnnouncementProvider;
import com.example.gocarrentspringbootapplication.impl.models.TestUserProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class AnnouncementBuilderTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private AnnouncementBuilder testAnnouncementBuilder;

    @Test
    void build() {
        Announcement announcement = setUp().build();

        Announcement testAnnouncement = TestAnnouncementProvider.getTestAnnouncement();
        assertEquals(testAnnouncement.getAuthor().getId(), announcement.getAuthor().getId());
        assertEquals(testAnnouncement.getCreatedAt(), announcement.getCreatedAt());
        assertEquals(testAnnouncement.getRentStatus(), announcement.getRentStatus());
        assertEquals(testAnnouncement.getAnnouncementDetails().getTitle(), announcement.getAnnouncementDetails().getTitle());
        assertEquals(testAnnouncement.getAnnouncementDetails().getAmount(), announcement.getAnnouncementDetails().getAmount());
        assertEquals(testAnnouncement.getAnnouncementDetails().getCurrency(), announcement.getAnnouncementDetails().getCurrency());
        assertEquals(testAnnouncement.getAnnouncementDetails().getCarBrand(), announcement.getAnnouncementDetails().getCarBrand());
        assertEquals(testAnnouncement.getAnnouncementDetails().getCarModel(), announcement.getAnnouncementDetails().getCarModel());
    }

    @Test
    void refresh() {
        AnnouncementBuilder announcementBuilder = (AnnouncementBuilder) setUp();
        announcementBuilder.refresh();
        Announcement announcement = new Announcement();
        announcement.setAnnouncementDetails(new AnnouncementDetails());
        assertEquals(announcement.getId(), announcementBuilder.build().getId());
        assertEquals(announcement.getAnnouncementDetails().getId(), announcementBuilder.build().getAnnouncementDetails().getId());
    }

    private IAnnouncementBuilder setUp() {
        Mockito.when(userRepository.findById(TestAnnouncementProvider.ID)).thenReturn(Optional.of(TestUserProvider.getTestUser()));
        return testAnnouncementBuilder
                .setTitle(TestAnnouncementProvider.TITLE)
                .setAmount(TestAnnouncementProvider.AMOUNT)
                .setCurrency(TestAnnouncementProvider.CURRENCY)
                .setTimeUnit(TestAnnouncementProvider.TIME_UNIT)
                .setCarBrand(TestAnnouncementProvider.CAR_BRAND)
                .setCarModel(TestAnnouncementProvider.CAR_MODEL)
                .setAuthor(TestAnnouncementProvider.ID);
    }
}