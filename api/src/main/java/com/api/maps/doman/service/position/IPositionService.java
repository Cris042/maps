package com.api.maps.doman.service.position;

import com.api.maps.utils.async.PositionResult;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

public interface IPositionService {
    long findPositionByDate(Date date, String nameActive);
    PositionResult getPositionResult(long id);
}
