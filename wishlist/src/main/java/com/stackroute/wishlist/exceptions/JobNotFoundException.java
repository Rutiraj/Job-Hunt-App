package com.stackroute.wishlist.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Job not found.")

public class JobNotFoundException extends Exception {

}
