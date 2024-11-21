package br.com.pipocaagil.corraagil.Reset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetTokenRepository extends JpaRepository<ResetToken, Long> {
    ResetToken findByToken(String token);
}
