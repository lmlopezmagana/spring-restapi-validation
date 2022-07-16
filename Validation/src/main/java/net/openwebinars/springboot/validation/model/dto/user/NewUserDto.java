package net.openwebinars.springboot.validation.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class NewUserDto {

    @NotEmpty
    private String fullname;

    @NotEmpty
    private String username;

    @NotEmpty
    @Email
    private String email;

    @URL
    private String avatar;

    @NotEmpty
    private String password;

    @NotEmpty
    private String verifyPassword;



}
