package com.kt5.mobileservice1.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt5.mobileservice1.model.LoginInfo;

public interface LoginInfoRepository extends JpaRepository<LoginInfo, Long> {

}
