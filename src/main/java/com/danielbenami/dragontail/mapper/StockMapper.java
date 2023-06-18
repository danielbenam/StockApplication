package com.danielbenami.dragontail.mapper;

import com.danielbenami.dragontail.dto.StockDTO;
import com.danielbenami.dragontail.model.Stock;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockMapper {

    private final ModelMapper modelMapper;

    public StockMapper() {
        this.modelMapper = new ModelMapper();
    }

    public StockDTO toDTO(Stock stock) {
        return modelMapper.map(stock, StockDTO.class);
    }

    public List<StockDTO> toDTOList(List<Stock> stocks) {
        return stocks.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
