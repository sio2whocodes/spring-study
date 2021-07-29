package sungshin.sooon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{10,}$")
    private String password;

    @NotBlank
    private String nickname;

    private LocalDateTime registeredDateTime;

    public Account toAccount(PasswordEncoder passwordEncoder){
        return Account.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .registeredDateTime(registeredDateTime)
                .build();
    }
}
