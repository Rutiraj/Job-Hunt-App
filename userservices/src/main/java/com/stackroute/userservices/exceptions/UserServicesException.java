package com.stackroute.userservices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Please provide proper credentials")
public class UserServicesException extends Exception {

}
