package com.mycompany.app.service;

import com.mycompany.app.dto.OPResult;
import com.mycompany.app.service.impl.ProfileServiceImpl;
import org.junit.Test;

public class ProfileServiceTest {

    private final ProfileService profileService = ProfileServiceImpl.INSTANCE;

    @Test
    public void register() {
        OPResult result = profileService.register("a333", "eqw3212", "233");
        OPResult result2 = profileService.register("a333", "eqw3212", "233");
        OPResult result3 = profileService.register("a333", "eqw3212", "233");
        System.out.println(result.getExtraInfo().getState());
    }
}
