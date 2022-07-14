package net.openwebinars.springboot.validation.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.openwebinars.springboot.validation.model.User;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetUserDto {

    private Long id;
    private String fullname;
    private String username;
    private String email;
    private String avatar;

    public static GetUserDto fromUser(User user) {
        return GetUserDto.builder()
                .id(user.getId())
                .fullname(user.getFullname())
                .username(user.getUsername())
                .avatar(user.getAvatar())
                .email(user.getEmail())
                .build();

    }

}
