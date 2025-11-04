package com.jonathan.todo.dto.response;

import java.time.LocalDateTime;

public class WorkspaceResponseDTO {
	private final Long workspaceId;
	private final String workspaceName;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;
	
	public WorkspaceResponseDTO(Long workspaceId, String workspaceName, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.workspaceId = workspaceId;
		this.workspaceName = workspaceName;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getWorkspaceId() {
		return workspaceId;
	}

	public String getWorkspaceName() {
		return workspaceName;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}
