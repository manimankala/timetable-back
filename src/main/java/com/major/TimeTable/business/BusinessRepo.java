package com.major.TimeTable.business;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BusinessRepo extends JpaRepository<Business, UUID> {
}
