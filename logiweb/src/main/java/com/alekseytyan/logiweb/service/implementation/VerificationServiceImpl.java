package com.alekseytyan.logiweb.service.implementation;

import com.alekseytyan.logiweb.dao.api.VerificationTokenDao;
import com.alekseytyan.logiweb.dto.UserDTO;
import com.alekseytyan.logiweb.dto.VerificationTokenDTO;
import com.alekseytyan.logiweb.entity.VerificationToken;
import com.alekseytyan.logiweb.service.api.UserService;
import com.alekseytyan.logiweb.service.api.VerificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VerificationServiceImpl
        extends AbstractServiceImpl<VerificationToken, VerificationTokenDao, VerificationTokenDTO, Long>
        implements VerificationService {

    private final UserService userService;

    @Autowired
    public VerificationServiceImpl(VerificationTokenDao dao, ModelMapper mapper, UserService userService) {
        super(dao, mapper, VerificationTokenDTO.class, VerificationToken.class);

        this.userService = userService;
    }

    @Override
    @Transactional
    public void createVerificationToken(UserDTO userDTO, String token) {
        getDao().createVerificationToken(userService.convertToEntity(userDTO), token);
    }

    @Override
    @Transactional(readOnly = true)
    public VerificationTokenDTO getVerificationToken(String token) {
        return convertToDTO(getDao().getVerificationToken(token));
    }
}
