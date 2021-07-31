package sungshin.sooon.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import sungshin.sooon.config.TokenProvider;
import sungshin.sooon.dto.LoginRequestDto;
import sungshin.sooon.dto.RegisterRequestDto;
import sungshin.sooon.dto.TokenDto;
import sungshin.sooon.model.Account;
import sungshin.sooon.model.RefreshToken;
import sungshin.sooon.model.UserAccount;
import sungshin.sooon.repository.AccountRepository;
import sungshin.sooon.repository.RefreshTokenRepository;
import sungshin.sooon.util.SecurityUtil;

import javax.validation.Valid;

@Validated
@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    @Autowired
    private final AccountRepository accountRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 로그인한 유저 정보 반환 to @CurrentUser
    public Account getUserInfo() {
        return accountRepository.findAccountByEmail(SecurityUtil.getUserName());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByEmail(username);

        if(account == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserAccount(account);
    }

    // 로그인
    @Transactional
    public TokenDto login(LoginRequestDto dto) {
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = dto.toAuthentication();

        // 2. 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .email(authentication.getName())
                .tokenValue(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 5. 토큰 발급
        return tokenDto;
    }

    //회원가입
    @Transactional
    public Account register(@Valid RegisterRequestDto registerRequestDto){
        Account account = registerRequestDto.toAccount(passwordEncoder);
        return accountRepository.save(account);
    }

    //닉네임 중복확인
    @Transactional
    public boolean checkNickname(String nickname){
        return accountRepository.existsByNickname(nickname);
    }

    //이메일 중복확인
    @Transactional
    public boolean checkEmail(String email){
        return accountRepository.existsByEmail(email);
    }
}
