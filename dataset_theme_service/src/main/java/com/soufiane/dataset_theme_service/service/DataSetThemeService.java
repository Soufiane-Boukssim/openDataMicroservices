package com.soufiane.dataset_theme_service.service;

import com.soufiane.dataset_theme_service.dto.DataSetThemeResponse;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface DataSetThemeService {
    List<DataSetThemeResponse> getAllThemes();
    DataSetThemeResponse getThemeById(UUID uuid);
    DataSetThemeResponse getThemeByName(String name);
    DataSetThemeResponse saveTheme(String name, String description, MultipartFile file, String token) throws IOException;
    DataSetThemeResponse updateThemeById(UUID uuid, String name, String description, MultipartFile icon, String token) throws IOException;
    Boolean deleteThemeById(UUID uuid);
    Long getNumberOfTheme();
    byte[] getImage(String fileName) throws IOException;
}