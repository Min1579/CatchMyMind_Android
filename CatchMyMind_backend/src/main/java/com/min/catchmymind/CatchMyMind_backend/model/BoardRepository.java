package com.min.catchmymind.CatchMyMind_backend.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
