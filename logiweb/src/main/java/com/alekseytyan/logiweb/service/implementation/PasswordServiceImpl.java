package com.alekseytyan.logiweb.service.implementation;

import com.alekseytyan.logiweb.dao.api.PasswordResetTokenDao;
import com.alekseytyan.logiweb.dto.PasswordResetTokenDTO;
import com.alekseytyan.logiweb.dto.UserDTO;
import com.alekseytyan.logiweb.entity.auth.PasswordResetToken;
import com.alekseytyan.logiweb.service.api.PasswordService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl extends AbstractServiceImpl<PasswordResetToken, PasswordResetTokenDao, PasswordResetTokenDTO, Long> implements PasswordService {
    public PasswordServiceImpl(PasswordResetTokenDao dao, ModelMapper mapper) {
        super(dao, mapper, PasswordResetTokenDTO.class, PasswordResetToken.class);
    }

    @Override
    public void createPasswordResetTokenForUser(UserDTO user, String token) {
        return getDao().save(new PasswordResetToken(user, token));
    }
}
