package com.es.practiarmar.service;

import com.es.practiarmar.dto.project.ProjectRequestDTO;
import com.es.practiarmar.dto.project.ProjectResponseDTO;
import com.es.practiarmar.model.Project;
import com.es.practiarmar.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements IProjectService{

    private final ProjectRepository repository;
    @Override
    public void saveProject(ProjectRequestDTO projectRequestDTO) {

    }

    @Override
    public List<ProjectResponseDTO> findAll() {
        return List.of();
    }

    @Override
    public Project findById(Long id) {
        return null;
    }

    @Override
    public void editProject(Project project) {

    }
}
