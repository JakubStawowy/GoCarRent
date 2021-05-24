package com.example.gocarrentspringbootapplication.impl.builders;

import com.example.gocarrentspringbootapplication.api.builders.IAnnouncementBuilder;
import com.example.gocarrentspringbootapplication.impl.dao.repositories.UserRepository;
import com.example.gocarrentspringbootapplication.impl.models.Announcement;
import com.example.gocarrentspringbootapplication.impl.models.AnnouncementDetails;
import com.example.gocarrentspringbootapplication.impl.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.Currency;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public final class AnnouncementBuilder implements IAnnouncementBuilder {

    private Announcement announcement;
    private final UserRepository userRepository;

    @Autowired
    public AnnouncementBuilder(UserRepository userRepository) {
        refresh();
        this.userRepository = userRepository;
    }

    @Override
    public Announcement build() {
        return announcement;
    }

    @Override
    public void refresh() {
        announcement = new Announcement();
        announcement.setAnnouncementDetails(new AnnouncementDetails());
    }

    @Override
    public IAnnouncementBuilder setTitle(String title) {
        announcement.getAnnouncementDetails().setTitle(title);
        return this;
    }

    @Override
    public IAnnouncementBuilder setAmount(String amount) {
        announcement.getAnnouncementDetails().setAmount(new BigDecimal(amount));
        return this;
    }

    @Override
    public IAnnouncementBuilder setCurrency(String currency) {
        announcement.getAnnouncementDetails().setCurrency(Currency.getInstance(currency));
        return this;
    }

    @Override
    public IAnnouncementBuilder setCarBrand(String brand) {
        announcement.getAnnouncementDetails().setCarBrand(brand);
        return this;
    }

    @Override
    public IAnnouncementBuilder setCarModel(String model) {
        announcement.getAnnouncementDetails().setCarModel(model);
        return this;
    }

    @Override
    public IAnnouncementBuilder setTimeUnit(String timeUnit) {
        announcement.getAnnouncementDetails().setTimeUnit(TimeUnit.of(ChronoUnit.valueOf(timeUnit)));
        return this;
    }

    @Override
    public IAnnouncementBuilder setAuthor(Long authorId) {
        Optional<User> optionalUser = userRepository.findById(authorId);
        optionalUser.ifPresent(user -> announcement.setAuthor(user));
        return this;
    }
}
