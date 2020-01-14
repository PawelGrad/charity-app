package pl.app.charity.Model.Token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    @Query(nativeQuery = true, value ="select * from tokens where uuid = ?")
    TokenEntity findByUuid(String uuid);
}
