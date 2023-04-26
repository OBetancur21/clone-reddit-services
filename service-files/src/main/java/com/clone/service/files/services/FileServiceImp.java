package com.clone.service.files.services;

import com.clone.service.files.dtos.FileDTO;
import com.clone.service.files.models.File;
import com.clone.service.files.repositories.FileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImp implements FileService{
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<FileDTO> findAll() {
        List<File> files = fileRepository.findAll();
        return files.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FileDTO> findByPostId(String postId) {
        List<File> files = fileRepository.findByPostId(postId);
        return files.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList())   ;
    }
    @Override
    public FileDTO findById(Long id) {
        File file = fileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Files not found with id " + id));
        return convertToDTO(file);
    }

    @Override
    public FileDTO create(FileDTO fileDTO) {
        File file = convertToEntity(fileDTO);
        File saveFile = fileRepository.save(file);
        return convertToDTO(saveFile);
    }

    @Override
    public FileDTO update(Long id, FileDTO fileDTO){
        File file = fileRepository.getReferenceById(id);
        file.setName(fileDTO.getName());
        File updateFile = fileRepository.save(file);
        return convertToDTO(updateFile);
    }

    @Override
    public void delete (Long id){fileRepository.deleteById(id);}

    public FileDTO convertToDTO(File file){
        FileDTO fileDTO = new FileDTO();
        fileDTO.setId(file.getId());
        fileDTO.setName(file.getName());
        fileDTO.setPostId(file.getPostId());
        return fileDTO;
    }

    public File convertToEntity(FileDTO fileDTO){
        File file = new File();
        file.setId(fileDTO.getId());
        file.setName(fileDTO.getName());
        file.setPostId(fileDTO.getPostId());
        return file;
    }
}
