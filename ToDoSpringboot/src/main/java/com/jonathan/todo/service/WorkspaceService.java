package com.jonathan.todo.service;

import java.util.List;

import com.jonathan.todo.dto.response.WorkspaceResponseDTO;

public interface WorkspaceService {
	List<WorkspaceResponseDTO> getWorkspacesByUserId(Long userId);
}
