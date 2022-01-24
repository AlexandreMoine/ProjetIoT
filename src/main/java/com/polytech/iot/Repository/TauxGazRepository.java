package com.polytech.iot.Repository;

import com.polytech.iot.Domain.TauxGazEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TauxGazRepository extends JpaRepository<TauxGazEntity, Integer> {

}
