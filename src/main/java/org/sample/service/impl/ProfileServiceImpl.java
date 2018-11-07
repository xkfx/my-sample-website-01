package org.sample.service.impl;

import org.sample.dao.ProfileDAO;
import org.sample.dao.impl.ProfileDAOImpl;
import org.sample.dto.OnesProfileResult;
import org.sample.dto.Validation;
import org.sample.entity.Profile;
import org.sample.enums.OnesProfileEnum;
import org.sample.manager.ConnectionProxy;
import org.sample.service.ProfileService;
import org.sample.util.FormatValidator;

public class ProfileServiceImpl implements ProfileService {

    public static final ProfileService INSTANCE = new ProfileServiceImpl();

    private ProfileServiceImpl() {}

    private final ProfileDAO profileDAO = ProfileDAOImpl.INSTANCE;

    @Override
    public Profile getProfileByUsername(String username) {
        return null;
    }

    @Override
    public OnesProfileResult register(String username, String password, String nickname) {
        Validation validation = FormatValidator.validateRegistration(username, password, nickname);
        if (!validation.isSuccess()) {
            return new OnesProfileResult(false, validation.getError());
        }

        Profile profile = new Profile(username, password, nickname);
        try {
            int i = profileDAO.saveProfile(profile);
            ConnectionProxy.close();
            if (i == 0) {
                return new OnesProfileResult(false, OnesProfileEnum.PROFILE_EXISTED);
            } else {
                return new OnesProfileResult(true, OnesProfileEnum.REGISTER_SUCCESS);
            }
        } catch (Exception e) {
            // TODO 日志
            System.out.println(e);
            return new OnesProfileResult(false, OnesProfileEnum.INNER_ERROR);
        }
    }
}
