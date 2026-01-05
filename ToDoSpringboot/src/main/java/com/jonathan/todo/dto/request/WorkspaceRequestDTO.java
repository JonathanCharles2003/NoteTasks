package com.jonathan.todo.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class WorkspaceRequestDTO {
	@NotNull
	private final String workspaceName; 

	@JsonCreator
	public WorkspaceRequestDTO(@JsonProperty("workspaceName") String workspaceName) {
		this.workspaceName = workspaceName;
	}

	public String getWorkspaceName() {
		return workspaceName;
	}
	
}
