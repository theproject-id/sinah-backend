package br.com.sinah.ward.repository;

import br.com.sinah.ward.model.WardModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WardRepository extends JpaRepository<WardModel, UUID> {
}
