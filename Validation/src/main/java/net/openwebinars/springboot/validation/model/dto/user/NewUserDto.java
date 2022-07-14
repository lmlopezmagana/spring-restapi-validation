package net.openwebinars.springboot.validation.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class NewUserDto {

    private String fullname;
    private String username;
    private String email;
    private String avatar;
    private String password;
    private String verifyPassword;



}
