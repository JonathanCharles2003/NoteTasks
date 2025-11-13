package com.jonathan.todo.dto.response;

import java.time.Instant;

public class WorkspaceResponseDTO {
	private final Long workspaceId;
	private final String workspaceName;
	private final Instant createdAt;
	private final Instant updatedAt;
	
	public WorkspaceResponseDTO(Long workspaceId, String workspaceName, Instant createdAt, Instant updatedAt) {
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

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}
}
