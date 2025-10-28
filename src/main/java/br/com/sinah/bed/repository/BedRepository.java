package br.com.sinah.bed.repository;

import br.com.sinah.bed.model.BedModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BedRepository extends JpaRepository<BedModel, UUID> {}
