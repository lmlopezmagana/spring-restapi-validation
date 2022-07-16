package net.openwebinars.springboot.validation.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.openwebinars.springboot.validation.validation.annotation.UniqueUsername;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class NewUserDto {

    @NotEmpty(message = "{userDto.fullname.notempty}")
    private String fullname;

    @UniqueUsername(message = "{newUserDto.username.unique}")
    @NotEmpty(message = "{newUserDto.username.notempty}")
    private String username;

    @NotEmpty(message = "{newUserDto.email.notempty}")
    @Email(message = "{newUserDto.email.email}")
    private String email;

    @URL(message = "{userDto.avatar.url}")
    private String avatar;

    @NotEmpty(message = "{userDto.password.notempty}")
    private String password;

    @NotEmpty(message = "{userDto.verifypassword.notempty}")
    private String verifyPassword;

}
