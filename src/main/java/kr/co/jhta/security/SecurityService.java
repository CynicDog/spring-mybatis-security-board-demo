package kr.co.jhta.security;

import kr.co.jhta.repository.RoleDao;
import kr.co.jhta.repository.UserDao;
import kr.co.jhta.security.model.SecurityUser;
import kr.co.jhta.vo.Role;
import kr.co.jhta.vo.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityService implements UserDetailsService {

    private final UserDao userDao;
    private final RoleDao roleDao;

    public SecurityService(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userDao.findByEmail(email);

        List<Role> roles = roleDao.findByUserId(user.getId());
        roles.forEach(role -> {
            user.addRole(role.getRoleName());
        });

        UserDetails userDetails = new SecurityUser(user);

        return userDetails;
    }
}
