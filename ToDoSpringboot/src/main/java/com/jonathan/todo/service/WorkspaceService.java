package com.jonathan.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jonathan.todo.dto.response.WorkspaceResponseDTO;
import com.jonathan.todo.model.Workspace;
import com.jonathan.todo.repository.WorkspaceRepository;

@Service
public class WorkspaceService {
	private final WorkspaceRepository workspaceRepo;
	
	public WorkspaceService(WorkspaceRepository workspaceRepo) {
		this.workspaceRepo = workspaceRepo;
	}
	
	@Transactional(readOnly = true)
	public List<WorkspaceResponseDTO> getWorkspacesByUserId(Long userId) {
		List<Workspace> workspaces = workspaceRepo.findByUserUserIdAndDeletedAtIsNull(userId);
		return workspaces.stream()
				.map(workspace -> new WorkspaceResponseDTO(
						workspace.getWorkspaceId(),
						workspace.getWorkspaceName(),
						workspace.getCreatedAt(),
						workspace.getUpdatedAt()))
				.toList();
	}
}
