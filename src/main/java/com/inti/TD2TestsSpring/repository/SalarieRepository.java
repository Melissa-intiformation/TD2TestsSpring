package com.inti.TD2TestsSpring.repository;

import java.util.List;

import com.inti.TD2TestsSpring.model.Salarie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalarieRepository extends JpaRepository<Salarie, Integer>
{
}
