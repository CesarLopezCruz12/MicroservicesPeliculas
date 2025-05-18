package com.prueba.character.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.character.entity.CharacterModel;

@Repository
public interface CharacterRepository  extends JpaRepository<CharacterModel,Integer>{

}
