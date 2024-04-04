package com.api.maps.doman.builder;

import com.api.maps.data.entity.Position;
import com.api.maps.doman.dto.PositionDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PositionBuilder {

    public Position fromEntity(PositionDTO positionDTO) {
        return new Position(
                positionDTO.getId() != null ? positionDTO.getId() : null,
                positionDTO.getNameActive(),
                positionDTO.getTypeFinancialAssets(),
                positionDTO.getAmountAvailable(),
                positionDTO.getValueMarketplace(),
                positionDTO.getValueYield(),
                positionDTO.getValueGain()
        );
    }

    public PositionDTO fromDTO(Position position) {
        return new PositionDTO(
                position.getId(),
                position.getNameActive(),
                position.getTypeFinancialAssets(),
                position.getAmountAvailable(),
                position.getValueMarketplace(),
                position.getValueYield(),
                position.getValueGain()
        );
    }

    public List<PositionDTO> fromListDTO(List<Position> position){
        return position.stream().map(this::fromDTO).collect(Collectors.toList());
    }
}
