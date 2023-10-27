package com.stock.tracker.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.tracker.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
}
