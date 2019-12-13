package pl.coderslab.charity.Model.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.Model.UserEntity.UserEntity;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(nativeQuery = true, value= "select users.id, users.email, enabled, password " +
            "from users inner join authorities a on users.id = a.user_id " +
            "where authority = 'ROLE_ADMIN'")
    List<UserEntity> findAllAdmins();

    @Query(nativeQuery = true, value= "select users.id, users.email, enabled, password " +
            "from users inner join authorities a on users.id = a.user_id " +
            "where authority = 'ROLE_USER'")
    List<UserEntity> findAllUsers();

    @Query(nativeQuery = true, value ="select * from users where email = ?")
    UserEntity findByEmail(String email);


}
