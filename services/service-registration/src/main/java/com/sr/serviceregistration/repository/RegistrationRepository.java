package com.sr.serviceregistration.repository;

import com.sr.serviceregistration.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RegistrationRepository  extends JpaRepository<Registration, UUID> {
    @Query("SELECT r FROM Registration r WHERE r.id_student = :idStudent")
    List<Registration> findAllById_student(@Param("idStudent") String idStudent);
}
