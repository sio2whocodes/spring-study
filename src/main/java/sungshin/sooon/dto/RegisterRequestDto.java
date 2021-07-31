package sungshin.sooon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import sungshin.sooon.model.Account;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    private String email;
    private String password;
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
