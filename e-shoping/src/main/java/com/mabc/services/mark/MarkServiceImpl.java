package com.mabc.services.mark;

import com.mabc.entities.Mark;
import com.mabc.repositories.MarkRepository;
import com.mabc.dto.MarkDTO;

import org.springframework.stereotype.Service;


@Service
public class MarkServiceImpl implements MarkService {
    private final MarkRepository repository;

    public MarkServiceImpl(MarkRepository markRepository) {
        this.repository = markRepository;
    }

    @Override
    public MarkDTO saveMark(MarkDTO markDTO) {
        this.validaDatos(markDTO);

        Mark mark;
        if (markDTO.getId() == null) {
            mark = new Mark();
        } else {
            mark = this.repository.findById(markDTO.getId()).orElse(new Mark());
        }

        mark.setName(markDTO.getName());
        mark.setActive(markDTO.getActive());

        Mark savedMark = repository.save(mark);
        if(savedMark == null){
            return null;
        }
        markDTO.setId(savedMark.getId());
        return markDTO;
    }

    @Override
    public MarkDTO getMarkById(Long id) {
        Mark mark = repository.findById(id).orElse(null);
        if(mark == null){
            return null;
        }

        return new MarkDTO(mark.getId(), mark.getName(), mark.getActive());
    }

    @Override
    public void deleteMark(Long id) {
        if(!this.repository.existsById(id)){
            throw new IllegalArgumentException("Imposible eliminar, Marca inexistente");
        }
        repository.deleteById(id);
    }

    private void validaDatos(MarkDTO dto) throws IllegalArgumentException{
        Boolean isValid = true;
        isValid = dto.getName() != null && !dto.getName().isEmpty();
        isValid = isValid && dto.getActive() != null;
        if(!isValid){
            throw new IllegalArgumentException("Marca con datos no válidos o incompletos");
        }
    }
}