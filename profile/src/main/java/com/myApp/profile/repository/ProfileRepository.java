package com.myApp.profile.repository;

import com.myApp.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<UserProfile, Long> {

    @Query("SELECT profile FROM UserProfile profile WHERE profile.id = ?1")
    Boolean existsById(long id);

    @Query("SELECT profile FROM UserProfile profile WHERE profile.name = ?1")
    Boolean existsByName(String name);
}
