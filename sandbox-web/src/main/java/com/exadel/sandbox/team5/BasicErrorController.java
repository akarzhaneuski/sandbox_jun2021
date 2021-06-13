/**
 * OVERRIDING RestExceptionHandler WHEN ACTIVE!!!
 */

/*
package com.exadel.sandbox.team5;

import error.ApiError;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class BasicErrorController implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public ResponseEntity<Object> handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        return new ResponseEntity("Page Not FOUND: ", HttpStatus.resolve(statusCode));
    }


}*/
