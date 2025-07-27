package com.es.practiarmar.repository;

import com.es.practiarmar.model.Customer;
import com.es.practiarmar.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    @Query("SELECT t FROM token t inner join employee c on t.employee.id= c.id WHERE c.id= :employeeId and (t.expired=false or t.revoked=false)")
    List<Token> findAllValidTokenByCustomer(@Param("employeeId") Long id);
    @Query("SELECT t.employee FROM token t WHERE t.token= :token ")
    Customer getCustomerByToken(@Param("token")String token);
    Optional<Token> findTokenByToken(String token);
}
