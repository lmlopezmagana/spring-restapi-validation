package net.openwebinars.springboot.validation.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.openwebinars.springboot.validation.validation.annotation.FieldsValueMatch;
import net.openwebinars.springboot.validation.validation.annotation.PasswodsMatch;
import net.openwebinars.springboot.validation.validation.annotation.StrongPassword;
import net.openwebinars.springboot.validation.validation.annotation.UniqueUsername;
import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "password", fieldMatch = "verifyPassword",
                message = "{newUserDto.password.nomatch}"
        ),
        @FieldsValueMatch(
                field = "email", fieldMatch = "verifyEmail",
                message = "{newUserDto.emails.nomatch}"
        )
})
public class NewUserDto {

    @NotEmpty(message = "{userDto.fullname.notempty}")
    private String fullname;

    @UniqueUsername(message = "{newUserDto.username.unique}")
    @NotEmpty(message = "{newUserDto.username.notempty}")
    private String username;

    @NotEmpty(message = "{newUserDto.email.notempty}")
    @Email(message = "{newUserDto.email.email}")
    private String email;

    @Email(message = "{newUserDto.email.email}")
    private String verifyEmail;

    @URL(message = "{userDto.avatar.url}")
    private String avatar;

    @StrongPassword
    @NotEmpty(message = "{userDto.password.notempty}")
    private String password;

    @NotEmpty(message = "{userDto.verifypassword.notempty}")
    private String verifyPassword;

}
