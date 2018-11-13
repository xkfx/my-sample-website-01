package com.mycompany.app.service.impl;

import com.mycompany.app.service.ProfileService;
import com.mycompany.app.dao.ProfileDAO;
import com.mycompany.app.dao.impl.ProfileDAOImpl;
import com.mycompany.app.dto.OPResult;
import com.mycompany.app.dto.Validation;
import com.mycompany.app.entity.Profile;
import com.mycompany.app.enums.OPEnum;
import com.mycompany.app.manager.ConnectionProxy;
import com.mycompany.app.util.FormatValidator;

public class ProfileServiceImpl implements ProfileService {

    public static final ProfileService INSTANCE = new ProfileServiceImpl();

    private ProfileServiceImpl() {}

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
            int i = profileDAO.saveProfile(profile);
            ConnectionProxy.close();
            if (i == 0) {
                return new OPResult(false, OPEnum.PROFILE_EXISTED);
            } else {
                return new OPResult(true, OPEnum.REGISTER_SUCCESS);
            }
        } catch (Exception e) {
            // TODO 日志
            System.out.println(e);
            return new OPResult(false, OPEnum.INNER_ERROR);
        }
    }
}
