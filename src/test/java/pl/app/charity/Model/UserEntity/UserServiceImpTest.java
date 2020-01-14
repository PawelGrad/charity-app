package pl.app.charity.Model.UserEntity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.app.charity.Model.Token.TokenRepository;
import pl.app.charity.Utils.EmailServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImpTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @Mock
    private EmailServiceImpl emailService;
    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    UserServiceImp userService;

    private UserEntity user;

    @Before
    public void beforeEachTest() {
        user = new UserEntity();

    }

    @Test
    public void saveUser() {
        userService.saveUser(user);
        Assert.assertFalse(user.getEnabled());
        Assert.assertEquals(1,user.getTokens().size());
        Assert.assertEquals(1,user.getAuthorities().size());
    }

    @Test
    public void activateAccount() {
        userService.activateAccount(user);
        Assert.assertTrue(user.getEnabled());
        Assert.assertEquals(0,user.getTokens().size());

    }

    @Test
    public void addAdmin() {
        userService.addAdmin(user);
        Assert.assertTrue(user.getEnabled());
        Assert.assertEquals(1,user.getAuthorities().size());
    }

    @Test
    public void blockUser() {
        user.setEnabled(true);
        userService.blockUser(user);
        Assert.assertFalse(user.getEnabled());
    }

    @Test
    public void isAcceptable() {
        user.setPassword("1");
        user.setPasswordConfirmation("1");
        Assert.assertFalse(userService.isAcceptable(user));
        user.setPassword("Aaaaaaaaaaaaaaaaaaa1");
        user.setPasswordConfirmation("Aaaaaaaaaaaaaaaaaaa1");
        Assert.assertFalse(userService.isAcceptable(user));
        user.setPassword("Aaaaaaaaaaaaaa1");
        user.setPasswordConfirmation("Aaaaaaaaaaaaaaaaa1");
        Assert.assertFalse(userService.isAcceptable(user));
        user.setPassword("Aaa1!");
        user.setPasswordConfirmation("Aaa1!");
        Assert.assertFalse(userService.isAcceptable(user));
        user.setPassword("Cgggerwww1123!");
        user.setPasswordConfirmation("Cgggerwww1123!");
        Assert.assertTrue(userService.isAcceptable(user));
    }


}