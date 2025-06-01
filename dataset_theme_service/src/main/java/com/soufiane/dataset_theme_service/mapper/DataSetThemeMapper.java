package com.soufiane.dataset_theme_service.mapper;

import com.soufiane.dataset_theme_service.dto.DataSetThemeRequest;
import com.soufiane.dataset_theme_service.dto.DataSetThemeResponse;
import com.soufiane.dataset_theme_service.entity.DataSetTheme;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component @RequiredArgsConstructor
public class DataSetThemeMapper {

    private final ModelMapper modelMapper;

    public DataSetTheme convertToEntity(DataSetThemeRequest dataSetThemeRequest) {
        return modelMapper.map(dataSetThemeRequest, DataSetTheme.class);
    }

    public DataSetThemeResponse convertToResponse(DataSetTheme dataSetTheme) {
        return modelMapper.map(dataSetTheme, DataSetThemeResponse.class);
    }
}