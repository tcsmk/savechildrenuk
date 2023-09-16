package com.uk.savechildrenuk.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class represents error code and error description of the Exceptions.
 */
@Data
@AllArgsConstructor
public class ApiError {
    private Integer errorCode;
    private String errorDesc;

}
