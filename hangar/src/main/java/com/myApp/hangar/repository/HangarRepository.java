package com.myApp.hangar.repository;

import com.myApp.model.Hangar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HangarRepository extends JpaRepository<Hangar, Long> {

    Page<Hangar> findByStateTrue(Pageable pageable);

    @Query("Select hangar FROM Hangar hangar WHERE hangar.name LIKE :name% AND hangar.state = true")
    List<Hangar> findByNameWithTrueState(@Param("name") String name);

    @Query("SELECT hangar FROM Hangar hangar WHERE hangar.state = true")
    List<Hangar> findAllWithTrueState();

    @Query("SELECT hangar FROM  Hangar hangar WHERE hangar.name = ?1 and hangar.address = ?2")
    Hangar findHangarByNameAndAddress(String name, String address);

    @Query("SELECT hangar FROM  Hangar hangar WHERE hangar.name = ?1")
    Hangar findHangarByName(String name);

    boolean existsByName(String name);

    boolean existsByNameAndAddress(String name, String address);

}
