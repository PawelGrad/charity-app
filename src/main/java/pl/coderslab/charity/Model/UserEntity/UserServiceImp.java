package pl.coderslab.charity.Model.UserEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.Config.EmailServiceImpl;
import pl.coderslab.charity.Model.Authority.AuthorityEntity;
import pl.coderslab.charity.Model.Authority.AuthorityRepository;
import pl.coderslab.charity.Model.Token.TokenEntity;
import pl.coderslab.charity.Model.Token.TokenRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImp {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private EmailServiceImpl emailService;
    private TokenRepository tokenRepository;

    public UserServiceImp(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, EmailServiceImpl emailService, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.tokenRepository = tokenRepository;
    }

    public void saveUser(UserEntity user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(false);
        TokenEntity token = new TokenEntity(user);
        user.getTokens().add(token);
        user.getAuthorities().add(new AuthorityEntity("ROLE_USER",user));
        userRepository.save(user);
        emailService.accountActivationEmail(user.getEmail(),token.getUuid());
    }

    public void activateAccount(String uuid) {
        TokenEntity token = tokenRepository.findByUuid(uuid);
        UserEntity user = token.getUser();
        user.setEnabled(true);
        user.getTokens().remove(token);
        userRepository.save(user);

    }

    public void sendPasswordResetEmail(UserEntity user) {
        TokenEntity token = new TokenEntity(user);
        emailService.passwordRecoveryEmail(user.getEmail(), token.getUuid());
        tokenRepository.save(token);
    }

    public void addAdmin(UserEntity admin){
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setEnabled(true);
        admin.getAuthorities().add(new AuthorityEntity("ROLE_ADMIN",admin));
        userRepository.save(admin);
    };


    public void blockUser(Long id){
        UserEntity userEntity = userRepository.findById(id).orElse(null); //popraw
        userEntity.setEnabled(false);
        userRepository.save(userEntity);
    }

    public void updatePassword(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void removeUser(Long id){
        userRepository.deleteById(id);
    }

    public void updateUser(UserEntity user){
        userRepository.save(user);
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity getUserByEmail(String email) { return userRepository.findByEmail(email);}

    public List<UserEntity> getUsers(){
        return userRepository.findAllUsers();
    };

    public List<UserEntity> getAdmins(){
        return userRepository.findAllAdmins();
    };

    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName());}
}
