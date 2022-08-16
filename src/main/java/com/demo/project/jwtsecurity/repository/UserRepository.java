package com.demo.project.jwtsecurity.repository;

import com.demo.project.jwtsecurity.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 연관관계가 있는 엔티티를 조회할 경우 지연 로딩으로 설정되어 있으면 연관관계에서 종속된 엔티티는
    // 쿼리 실행 시 select 되지 않고 proxy 객체를 만들어 엔티티가 적용
    // Eager 조회
    @EntityGraph(attributePaths = "authorities")    // attributePaths = "User 객체와 Join할 객체"
    Optional<User> findOneWithAuthoritiesByUsername(String username);



}
