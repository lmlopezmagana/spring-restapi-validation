package net.openwebinars.springboot.validation.controller;

import lombok.RequiredArgsConstructor;
import net.openwebinars.springboot.validation.model.User;
import net.openwebinars.springboot.validation.model.dto.user.EditUserDto;
import net.openwebinars.springboot.validation.model.dto.user.EditUserPasswordDto;
import net.openwebinars.springboot.validation.model.dto.user.GetUserDto;
import net.openwebinars.springboot.validation.model.dto.user.NewUserDto;
import net.openwebinars.springboot.validation.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/")
public class UserController {


    private final UserService userService;

    @GetMapping("/")
    public List<GetUserDto> getAll() {

        return userService.findAll()
                .stream()
                .map(GetUserDto::fromUser)
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public GetUserDto getById(@PathVariable Long id) {
        return GetUserDto.fromUser(userService.findById(id));
    }


    @PostMapping("/register")
    public ResponseEntity<GetUserDto> register(@Valid  @RequestBody NewUserDto newUserDto) {

        User created = userService.save(newUserDto);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity
                .created(createdURI)
                .body(GetUserDto.fromUser(created));


    }

    @PutMapping("/details/{id}")
    public GetUserDto editDetails(@PathVariable Long id, @Valid @RequestBody EditUserDto editUserDto) {

        User edited = userService.editDetails(id, editUserDto);

        return GetUserDto.fromUser(edited);

    }

    @PutMapping("/password/{id}")
    public GetUserDto editDetails(@PathVariable Long id, @Valid @RequestBody EditUserPasswordDto editUserPasswordDto) {

        User edited = userService.changePassword(id, editUserPasswordDto);

        return GetUserDto.fromUser(edited);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
