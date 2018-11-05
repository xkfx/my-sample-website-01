package org.sample.service;

import org.junit.Test;
import org.sample.dto.OnesProfileResult;
import org.sample.service.impl.ProfileServiceImpl;

public class ProfileServiceTest {

    private final ProfileService profileService = ProfileServiceImpl.INSTANCE;

    @Test
    public void register() {
        OnesProfileResult result = profileService.register("a333", "eqw3212", "233");
        OnesProfileResult result2 = profileService.register("a333", "eqw3212", "233");
        OnesProfileResult result3 = profileService.register("a333", "eqw3212", "233");
        System.out.println(result.getExtraInfo().getState());
    }
}
