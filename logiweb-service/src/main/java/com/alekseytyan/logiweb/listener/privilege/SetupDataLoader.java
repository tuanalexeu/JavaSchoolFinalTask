package com.alekseytyan.logiweb.listener.privilege;

import com.alekseytyan.logiweb.entity.security.Privilege;
import com.alekseytyan.logiweb.entity.security.Role;
import com.alekseytyan.logiweb.entity.security.User;
import com.alekseytyan.logiweb.service.api.PrivilegeService;
import com.alekseytyan.logiweb.service.api.RoleService;
import com.alekseytyan.logiweb.service.api.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Listener is used to listen for app initialization in order to handle ROLES & PRIVILEGES
 */
@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    private final RoleService roleService;
    private final PrivilegeService privilegeService;

    @Autowired
    public SetupDataLoader(RoleService roleService,
                           PrivilegeService privilegeService) {
        this.roleService = roleService;
        this.privilegeService = privilegeService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
 
        if (alreadySetup) {
            return;
        }

        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
 
        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);


        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_EMPLOYEE", Collections.singletonList(readPrivilege));
        createRoleIfNotFound("ROLE_DRIVER", Collections.singletonList(readPrivilege));
        createRoleIfNotFound("ROLE_USER", Collections.singletonList(readPrivilege));

//        Role adminRole = roleService.findByName("ROLE_ADMIN");
//        User user = new User();
//        user.setFirstName("Test");
//        user.setLastName("Test");
//        user.setPassword(passwordEncoder.encode("test"));
//        user.setEmail("test@test.com");
//        user.setRoles(Arrays.asList(adminRole));
//        user.setEnabled(true);
//        userService.save(userService.convertToDTO(user));

        alreadySetup = true;
    }

    @Transactional
    public Privilege createPrivilegeIfNotFound(String name) {
 
        Privilege privilege = privilegeService.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeService.save(privilegeService.convertToDTO(privilege));
        }
        return privilege;
    }

    @Transactional
    public Role createRoleIfNotFound(
      String name, Collection<Privilege> privileges) {
 
        Role role = roleService.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleService.save(roleService.convertToDTO(role));
        }
        return role;
    }
}