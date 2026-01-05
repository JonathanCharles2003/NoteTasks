package com.jonathan.todo.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jonathan.todo.dto.request.WorkspaceRequestDTO;
import com.jonathan.todo.dto.response.WorkspaceResponseDTO;
import com.jonathan.todo.exception.BadRequestException;
import com.jonathan.todo.exception.ConflictException;
import com.jonathan.todo.exception.UserNotFoundException;
import com.jonathan.todo.model.User;
import com.jonathan.todo.model.Workspace;
import com.jonathan.todo.repository.WorkspaceRepository;
import com.jonathan.todo.util.MessageUtil;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {
	private final UserService userService;
	private final WorkspaceRepository workspaceRepo;
	private final MessageUtil messageUtil;
	
	public WorkspaceServiceImpl(WorkspaceRepository workspaceRepo, UserService userService, MessageUtil messageUtil) {
		this.workspaceRepo = workspaceRepo;
		this.userService = userService;
		this.messageUtil = messageUtil;
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
	
	public WorkspaceResponseDTO createWorkspaceForUser(WorkspaceRequestDTO request,Long userId) { 
	    User user = userService.getUserById(userId)
	            .orElseThrow(() -> new UserNotFoundException(
	                messageUtil.getMessage("user.notfound")));
		String workspaceName = normalizeAndValidateName(request.getWorkspaceName());	
		Workspace savedWorkspace = saveWorkspace(user, workspaceName);
		return new WorkspaceResponseDTO(savedWorkspace.getWorkspaceId(),savedWorkspace.getWorkspaceName(), savedWorkspace.getCreatedAt(), savedWorkspace.getUpdatedAt());
		
	}
	
    private String normalizeAndValidateName(String name) {
        if (name == null) {
            throw new BadRequestException("workspace.name.required");
        }

        String normalized = name.strip();

        if (normalized.isEmpty()) {
            throw new BadRequestException("workspace.name.empty");
        }

        if (normalized.length() > 50) {
            throw new BadRequestException("workspace.name.toolong");
        }

        return normalized;
    }
    
    @Transactional
    protected Workspace saveWorkspace(User user, String workspaceName) {
		Workspace workspace = new Workspace();
		workspace.setUser(user);
		workspace.setWorkspaceName(workspaceName);
		
		Workspace savedWorkspace;
		try {
			savedWorkspace = workspaceRepo.save(workspace);
		} catch (DataIntegrityViolationException ex) {
		    throw new ConflictException("workspace.duplicate");
		}
		
		return savedWorkspace;
    }

}
