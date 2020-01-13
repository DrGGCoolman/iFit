package de.hsba.ifit.daytime;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.hsba.ifit.daytime.Daytime.DaytimeName;

@Repository
public interface DaytimeRepository extends JpaRepository<Daytime, Integer> {

    List<Daytime> findAll();

    Daytime findByName(DaytimeName name);

}
