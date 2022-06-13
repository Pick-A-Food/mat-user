package com.matjo.pickafood.user.service;

import com.matjo.pickafood.user.domain.Food;
import com.matjo.pickafood.user.dto.FoodDTO;
import com.matjo.pickafood.user.dto.PageRequestDTO;
import com.matjo.pickafood.user.dto.PageResponseDTO;
import com.matjo.pickafood.user.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService{

    private final FoodRepository foodRepository;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<FoodDTO> list(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable();

        Page<Food> foodPage = foodRepository.searchAll(types, keyword, pageable);
        List<FoodDTO> foodDTOList = foodPage.getContent().stream()
                .map(food -> modelMapper.map(food, FoodDTO.class))
                .collect(Collectors.toList());
        return PageResponseDTO.<FoodDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(foodDTOList)
                .total(foodPage.getTotalElements())
                .build();
    }
}
