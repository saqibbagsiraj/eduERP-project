package com.eduerp.repository;

import com.eduerp.dto.response.RecentUserResponse;
import com.eduerp.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Query("""
        SELECT new com.eduerp.dto.response.RecentUserResponse(
            u.id,
            u.name,
            u.email,
            u.role.name,
            u.createdAt
        )
        FROM User u
        ORDER BY u.createdAt DESC
    """)
    List<RecentUserResponse> findRecentUsers(org.springframework.data.domain.Pageable pageable);

}