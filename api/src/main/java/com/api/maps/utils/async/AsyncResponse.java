package com.api.maps.utils.async;

import lombok.Data;

@Data
public class AsyncResponse {
    private final long executionId;

    public AsyncResponse(long executionId) {
        this.executionId = executionId;
    }

}