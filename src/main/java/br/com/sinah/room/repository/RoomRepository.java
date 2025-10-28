package br.com.sinah.room.repository;

import br.com.sinah.room.model.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<RoomModel, UUID> {}
