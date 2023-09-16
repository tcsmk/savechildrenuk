package com.uk.savechildrenuk.repository;

import com.uk.savechildrenuk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
