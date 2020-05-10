package com.richardson.seletorrotas.support;

import java.util.Date;

import com.richardson.seletorrotas.model.dto.ResponseMessage;

public class ResponseUtils {

	public static ResponseMessage criaResponseMessage(Integer status, String error, String message,
			String exceptionClass, String path) {
		ResponseMessage responseMessage = new ResponseMessage();

		responseMessage.setTimestamp(new Date(System.currentTimeMillis()));
		responseMessage.setStatus(status);
		responseMessage.setError(error);
		responseMessage.setException(exceptionClass);
		responseMessage.setMessage(message);
		responseMessage.setPath(path);

		return responseMessage;
	}
}
