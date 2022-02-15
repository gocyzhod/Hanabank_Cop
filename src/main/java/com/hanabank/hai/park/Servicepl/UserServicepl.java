package com.hanabank.hai.park.Servicepl;


import com.hanabank.hai.park.Dto.UserInfoDto;
import com.hanabank.hai.park.Entity.UserInfo;
import com.hanabank.hai.park.Repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor

public class UserServicepl {
    private final UserInfoRepository userInfoRepository;

    /**
     * @param  infoDto 회원정보가 들어 있는 DTO
     * @return 저장되는 회원의 PK
     */

    public Long save(UserInfoDto infoDto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        infoDto.setPassword(encoder.encode(infoDto.getPassword()));

        return userInfoRepository.save(UserInfo.builder()
        .email(infoDto.getEmail())
        .auth(infoDto.getAuth())
        .password(infoDto.getPassword())
                .build()).getCode();
    }

    /**
     * Spring Security 필수 메소드 구현
     * @param email 이메일
     * @return UserDetails
     * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
     */

    public UserInfo loadUserByUsername(String email) throws UsernameNotFoundException {

        return userInfoRepository.findByemail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));



    }



}
