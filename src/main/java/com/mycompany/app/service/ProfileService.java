package com.mycompany.app.service;

import com.mycompany.app.dto.OPResult;
import com.mycompany.app.entity.Profile;

public interface ProfileService {

    Profile getByUsername(String username);

    OPResult register(String username, String password, String nickname);
}
