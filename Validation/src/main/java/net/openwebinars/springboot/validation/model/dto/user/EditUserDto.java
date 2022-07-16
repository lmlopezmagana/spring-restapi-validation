package net.openwebinars.springboot.validation.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class EditUserDto {

    @NotEmpty(message = "{userDto.fullname.notempty}")
    private String fullname;

    @URL(message = "{usrDto.avatar.url}")
    private String avatar;

}
