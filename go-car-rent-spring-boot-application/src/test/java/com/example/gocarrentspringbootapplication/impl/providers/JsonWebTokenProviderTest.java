package com.example.gocarrentspringbootapplication.impl.providers;

import com.example.gocarrentspringbootapplication.impl.models.TestUserProvider;
import com.example.gocarrentspringbootapplication.security.providers.JsonWebTokenProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonWebTokenProviderTest {

    @Test
    void generateUserToken() {
        JsonWebTokenProvider jsonWebTokenProvider = new JsonWebTokenProvider();
        assertNotNull(jsonWebTokenProvider.generateUserToken(TestUserProvider.getTestUser()));
    }
}