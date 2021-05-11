package com.alekseytyan.logiweb.service.implementation;

import com.alekseytyan.logiweb.dao.api.PasswordResetTokenDao;
import com.alekseytyan.logiweb.dto.PasswordResetTokenDTO;
import com.alekseytyan.logiweb.dto.UserDTO;
import com.alekseytyan.logiweb.entity.auth.PasswordResetToken;
import com.alekseytyan.logiweb.service.api.PasswordService;
import com.alekseytyan.logiweb.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class PasswordServiceImpl extends AbstractServiceImpl<PasswordResetToken, PasswordResetTokenDao, PasswordResetTokenDTO, Long> implements PasswordService {

    private final UserService userService;

    @Autowired
    public PasswordServiceImpl(PasswordResetTokenDao dao, ModelMapper mapper, UserService userService) {
        super(dao, mapper, PasswordResetTokenDTO.class, PasswordResetToken.class);

        this.userService = userService;
    }

    @Override
    public void createPasswordResetTokenForUser(UserDTO user, String token) {
        getDao().createPasswordResetToken(userService.convertToEntity(user), token);
    }

    @Override
    public String validatePasswordResetToken(String token) {
        final PasswordResetToken passToken = getDao().findByToken(token);

        return !isTokenFound(passToken) ? "invalidToken"
                : isTokenExpired(passToken) ? "expired"
                : null;
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }
}
