package com.stackroute.wishlist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Job already exists")
public class JobAlreadyExistsException extends Exception {

}
