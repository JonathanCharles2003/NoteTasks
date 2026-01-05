package com.jonathan.todo.dto.response;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"timestamp","status","message","data"})
public class ApiResponse<T> {
	private final OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);
	private final int status;
	private final String message;
	private final T data;
	
	public ApiResponse(int status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public OffsetDateTime getTimestamp() {
		return timestamp;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}
}
