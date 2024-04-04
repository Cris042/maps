package com.api.maps.doman.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.io.Serializable;

@Data
@AllArgsConstructor
public class ExceptionResponse implements Serializable
{
    private String message;
    private String details;
}