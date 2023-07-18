package kr.co.jhta.service;

import kr.co.jhta.repository.BoardDao;
import kr.co.jhta.repository.RoleDao;
import kr.co.jhta.repository.UserDao;
import kr.co.jhta.vo.Board;
import kr.co.jhta.vo.Role;
import kr.co.jhta.vo.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class MvcService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final BoardDao boardDao;
    private final PasswordEncoder passwordEncoder;

    public MvcService(UserDao userDao, RoleDao roleDao, BoardDao boardDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.boardDao = boardDao;
        this.passwordEncoder = passwordEncoder;
    }

    public void insertUser(String email, String password) {

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        String roleName = "ROLE_USER";
        user.addRole(roleName);
        userDao.insertUser(user);

        Role role = new Role(user, roleName);
        roleDao.insertRole(role);
    }
    public User login(String email, String password) {
        return null;
    }

    public User findUserDetailById(int userId) {
        return null;
    }

    public void modifyUser(User user) {
    }

    public void createBoard(BoardDao boardDao) {
    }
    public List<Board> findBoardsPaginated(Map<String, Objects> params) {
        return null;
    }
    public void updateBoard(Board board) {
    }

}


