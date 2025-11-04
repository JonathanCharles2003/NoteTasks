package com.jonathan.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonathan.todo.model.Workspace;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long>{
	List<Workspace> findByUserUserIdAndDeletedAtIsNull(Long userId);
}
