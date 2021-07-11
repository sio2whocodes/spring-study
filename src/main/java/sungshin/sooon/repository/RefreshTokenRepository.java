package sungshin.sooon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sungshin.sooon.model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
