import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserServiceImplTest {
    public User user = Mockito.mock(User.class);
    public SecurityService secSer = Mockito.mock(SecurityService.class);
    public UserDAO userDAO = Mockito.mock(UserDAO.class);
    public UserServiceImpl userService = new UserServiceImpl(userDAO,secSer);

    @Test
    public void updateUserTest() throws Exception {
        userService.assignPassword(user);
        Mockito.verify(userDAO).updateUser(user);
    }

    @Test
    public void passwordBaruTest() throws Exception {
        userService.assignPassword(user);
        Mockito.verify(user).getPassword();
    }
}
