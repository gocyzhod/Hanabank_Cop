package com.hanabank.hai.park.Repository;

import com.hanabank.hai.park.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,String> {
    Optional<UserInfo> findByemail(String eamil);
}