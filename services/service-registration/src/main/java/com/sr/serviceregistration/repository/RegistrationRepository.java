package com.sr.serviceregistration.repository;

import com.sr.serviceregistration.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RegistrationRepository  extends JpaRepository<Registration, UUID> {

}
