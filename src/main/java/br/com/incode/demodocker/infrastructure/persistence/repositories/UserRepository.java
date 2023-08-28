package br.com.incode.demodocker.infrastructure.persistence.repositories;

import java.util.Optional;

import br.com.incode.demodocker.infrastructure.persistence.entities.User;
import br.com.incode.demodocker.infrastructure.util.BaseRepository;

public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByLogin(String login);

}
