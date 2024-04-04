package com.api.maps.doman.service.position;

import com.api.maps.data.entity.Position;
import com.api.maps.data.repository.IPositionRepository;
import com.api.maps.utils.async.PositionResult;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

@Service
@AllArgsConstructor
public class PositionServiceImpl implements IPositionService{

    private final IPositionRepository positionRepository;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final Map<Long, CompletableFuture<PositionResult>> processingResults = new ConcurrentHashMap<>();
    private final AtomicLong executionIdCounter = new AtomicLong(0);

    @Override
    public long findPositionByDate(Date date, String nameActive) {
        CompletableFuture<PositionResult> futureResult = CompletableFuture.supplyAsync(() -> {
            double amountAvailable = positionRepository.getAmountAvailableByNameActive(nameActive, date);
            double valueMarketplace = positionRepository.getValueMarketplaceByNameActive(nameActive, date);
            double valueGain = 0.0;
            double valueYield = positionRepository.getValueYieldByNameActive(nameActive, date);

            return new PositionResult(amountAvailable, valueMarketplace,valueGain, valueYield, nameActive);
        }, executorService);

        long executionId = generateExecutionId();
        processingResults.put(executionId, futureResult);
        return executionId;
    }

    private long generateExecutionId() {
        return executionIdCounter.incrementAndGet();
    }

    @Override
    public PositionResult getPositionResult(long id) {
        CompletableFuture<PositionResult> futureResult = processingResults.get(id);
        if (futureResult == null) {
            return null;
        }
        return futureResult.join();
    }
}
