package com.sublimado.repository;

import com.sublimado.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByEmail(String email);
    boolean existsByDni(String dni);
}
