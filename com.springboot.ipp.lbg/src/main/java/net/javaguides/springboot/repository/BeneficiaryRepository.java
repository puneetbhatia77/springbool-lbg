package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Beneficiary;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long>{

}
