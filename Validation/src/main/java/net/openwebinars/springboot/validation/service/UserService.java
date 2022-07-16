package net.openwebinars.springboot.validation.service;

import lombok.RequiredArgsConstructor;
import net.openwebinars.springboot.validation.model.User;
import net.openwebinars.springboot.validation.model.dto.user.EditUserDto;
import net.openwebinars.springboot.validation.model.dto.user.EditUserPasswordDto;
import net.openwebinars.springboot.validation.model.dto.user.NewUserDto;
import net.openwebinars.springboot.validation.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public List<User> findAll() {

        List<User> result = repository.findAll();

        if (result.isEmpty())
            throw new EntityNotFoundException("No users with this search criteria");

        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user with id: " + id));

    }

    /**
     * Almacenamos el nuevo usuario con la contarseña
     * cifrada con BCrypt
     * @param newUserDto Datos del nuevo usuario
     * @return Usuario creado
     */
    public User save(NewUserDto newUserDto) {

        return repository.save(
                User.builder()
                .username(newUserDto.getUsername())
                .password(passwordEncoder.encode(newUserDto.getPassword()))
                .avatar(newUserDto.getAvatar())
                .fullname(newUserDto.getFullname())
                .email(newUserDto.getEmail())
                .build());


    }

    /**
     * Se editan solamente algunos datos del usuario.
     * El username, el email y password no se pueden modificar
     * @param editUserDto Nuevo avatar o fullname
     * @return Usuario modificado
     */
    public User editDetails(Long id, EditUserDto editUserDto) {

        return repository.findById(id)
                .map(user -> {
                    user.setAvatar(editUserDto.getAvatar());
                    user.setFullname(editUserDto.getFullname());
                    return repository.save(user);
                })
                .orElseThrow(() ->new EntityNotFoundException("No user with id: " + id));


    }


    public User changePassword(Long id, EditUserPasswordDto editUserPasswordDto) {

        return repository.findById(id)
                .map(user -> {
                    user.setPassword(passwordEncoder.encode(editUserPasswordDto.getPassword()));
                    return repository.save(user);
                })
                .orElseThrow(() ->new EntityNotFoundException("No user with id: " + id));


    }

    public void delete(Long id) {

        if (repository.existsById(id))
            repository.deleteById(id);

    }

    public boolean userExists(String username) {
        return repository.existsByUsername(username);
    }


}
