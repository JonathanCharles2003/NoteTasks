package com.jonathan.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonathan.todo.dto.request.WorkspaceRequestDTO;
import com.jonathan.todo.dto.response.ApiResponse;
import com.jonathan.todo.dto.response.WorkspaceResponseDTO;
import com.jonathan.todo.service.WorkspaceService;
import com.jonathan.todo.util.MessageUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/workspaces")
public class WorkspaceController {
	
	private final WorkspaceService workspaceService;
	private final MessageUtil messageUtil;
	
	public WorkspaceController(WorkspaceService workspaceService, MessageUtil messageUtil) {
		this.workspaceService = workspaceService;
		this.messageUtil = messageUtil;
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<ApiResponse<List<WorkspaceResponseDTO>>> getWorkspacesByUserId(@PathVariable Long userId) {
		List<WorkspaceResponseDTO> workspaces = workspaceService.getWorkspacesByUserId(userId);
		String message =  workspaces.isEmpty() 
				? messageUtil.getMessage("workspace.notfound") 
				: messageUtil.getMessage("workspace.fetch.success");
		ApiResponse<List<WorkspaceResponseDTO>> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), message, workspaces);
		return ResponseEntity.ok(apiResponse); 
	}
	
	@PostMapping 
	public ResponseEntity<ApiResponse<WorkspaceResponseDTO>> createWorkspace(@RequestHeader("X-User-Id") Long userId, @Valid @RequestBody WorkspaceRequestDTO request){ //check if theres a better way to actually get the userId// check if the naming is fine
		WorkspaceResponseDTO workspace = workspaceService.createWorkspaceForUser(request, userId); //pass fields ig for god knows why???
		String message = messageUtil.getMessage("workspace.create.success");
		ApiResponse<WorkspaceResponseDTO> apiResponse = new ApiResponse<>(HttpStatus.CREATED.value(),message,workspace);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}
}
