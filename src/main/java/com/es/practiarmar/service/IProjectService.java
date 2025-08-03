package com.es.practiarmar.service;

import com.es.practiarmar.dto.project.ProjectRequestDTO;
import com.es.practiarmar.dto.project.ProjectResponseDTO;
import com.es.practiarmar.model.Project;

import java.util.List;

public interface IProjectService {
    void  saveProject(ProjectRequestDTO projectRequestDTO);
    List<ProjectResponseDTO> findAll();
    Project findById(Long id);
    void editProject(Project project);
}
