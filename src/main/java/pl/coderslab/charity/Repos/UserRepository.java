package pl.coderslab.charity.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.Model.UserEntity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
