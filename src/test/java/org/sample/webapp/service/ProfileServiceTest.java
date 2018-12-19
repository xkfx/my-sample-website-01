package org.sample.webapp.service;

import org.sample.webapp.dto.ServiceResult;
import org.sample.webapp.service.impl.ProfileServiceImpl;
import org.junit.Test;

public class ProfileServiceTest {

    private final ProfileService profileService = ProfileServiceImpl.INSTANCE;

    @Test
    public void register() {
        ServiceResult result = profileService.register("a333", "eqw3212", "233");
    }
}
