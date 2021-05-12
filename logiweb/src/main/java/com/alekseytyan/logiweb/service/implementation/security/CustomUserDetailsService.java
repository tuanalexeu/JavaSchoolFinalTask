package com.alekseytyan.logiweb.service.implementation.security;

import com.alekseytyan.logiweb.dto.UserDTO;
import com.alekseytyan.logiweb.entity.security.Privilege;
import com.alekseytyan.logiweb.entity.security.Role;
import com.alekseytyan.logiweb.service.api.RoleService;
import com.alekseytyan.logiweb.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service("userDetailService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
      throws UsernameNotFoundException {
 
        UserDTO user = userService.findById(email);
        if (user == null) {
            return new org.springframework.security.core.userdetails.User(
              " ", " ", true, true, true, true, 
              getAuthorities(Collections.singletonList(
                      roleService.findByName("ROLE_USER"))));
        }

        return new org.springframework.security.core.userdetails.User(
          user.getEmail(), user.getPassword(), user.isEnabled(), true, true,
          true, getAuthorities(userService.convertToEntity(user).getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
 
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {
 
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}