package org.sample.webapp.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sample.webapp.dao.ProfileDAO;
import org.sample.webapp.dao.impl.ProfileDAOImpl;
import org.sample.webapp.dto.OPResult;
import org.sample.webapp.dto.Validation;
import org.sample.webapp.entity.Profile;
import org.sample.webapp.enums.OPEnum;
import org.sample.webapp.manager.ConnectionProxy;
import org.sample.webapp.service.ProfileService;
import org.sample.webapp.util.FormatValidator;

public class ProfileServiceImpl implements ProfileService {

    public static final ProfileService INSTANCE = new ProfileServiceImpl();

    private ProfileServiceImpl() {}

    private static final Logger LOGGER = LogManager.getLogger();
    private final ProfileDAO profileDAO = ProfileDAOImpl.INSTANCE;

    @Override
    public Profile getByUsername(String username) {
        return null;
    }

    @Override
    public OPResult register(String username, String password, String nickname) {
        Validation validation = FormatValidator.validateRegistration(username, password, nickname);
        if (!validation.isSuccess()) {
            // 校验失败返回false以及失败的理由
            return new OPResult(false, validation.getError());
        }

        Profile profile = new Profile(username, password, nickname);
        try {
            LOGGER.info("ProfileService - saveProfile began: {}", profile);
            final long start = System.currentTimeMillis();
            int i = profileDAO.saveProfile(profile);
            final long end = System.currentTimeMillis();
            LOGGER.info("ProfileService - end of saveProfile: number of rows affected={}, {}ms", i, end - start);
            ConnectionProxy.close();
            if (i == 0) {
                return new OPResult(false, OPEnum.PROFILE_EXISTED);
            } else {
                return new OPResult(true, OPEnum.REGISTER_SUCCESS);
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred while registering - {}", e.getMessage());
            return new OPResult(false, OPEnum.INNER_ERROR);
        }
    }
}
