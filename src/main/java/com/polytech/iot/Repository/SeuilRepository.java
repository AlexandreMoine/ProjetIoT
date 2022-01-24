package com.polytech.iot.Repository;

import com.polytech.iot.Domain.SeuilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeuilRepository extends JpaRepository<SeuilEntity, Integer> {

}
