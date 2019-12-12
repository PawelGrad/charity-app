package pl.coderslab.charity.Model.UserEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.Model.Authority.AuthorityEntity;
import pl.coderslab.charity.Model.Authority.AuthorityRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImp {

    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, AuthorityRepository authorityRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(UserEntity user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        userRepository.save(user);
        authorityRepository.save(new AuthorityEntity("ROLE_USER",user));
    };
    public void addAdmin(UserEntity admin){
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setEnabled(true);
        userRepository.save(admin);
        authorityRepository.save(new AuthorityEntity("ROLE_ADMIN",admin));
    };

    public void removeUser(Long id){
        userRepository.deleteById(id);
    }

    public void updateUser(UserEntity user){
        userRepository.save(user);
    }

    public void blockUser(Long id){
        UserEntity userEntity = userRepository.findById(id).orElse(null); //popraw
        userEntity.setEnabled(false);
        userRepository.save(userEntity);
    }

    public void updatePassword(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

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
