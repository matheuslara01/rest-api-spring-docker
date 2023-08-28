package br.com.incode.demodocker.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.incode.demodocker.application.dtos.UserDTO;
import br.com.incode.demodocker.application.dtos.UserInput;
import br.com.incode.demodocker.infrastructure.persistence.entities.User;
import br.com.incode.demodocker.infrastructure.persistence.repositories.UserRepository;
import br.com.incode.demodocker.infrastructure.util.BaseService;

@Service
public class UserService extends BaseService<User, Long> {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public List<UserDTO> findAllUsers() {
        return findAll().stream().map(UserDTO::new).toList();
    }

    public UserDTO findUserById(Long id) {
        return new UserDTO(findById(id));
    }

    public UserDTO saveUser(UserInput userInput) {

        User user = new User(userInput);

        user = saveReturnEntity(user, "Error when saving!");

        User userPersisted = findById(user.getId());

        return new UserDTO(userPersisted);
    }

}
