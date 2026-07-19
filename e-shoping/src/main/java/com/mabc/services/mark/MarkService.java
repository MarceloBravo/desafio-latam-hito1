package com.mabc.services.mark;

import com.mabc.entities.Mark;
import com.mabc.dto.MarkDTO;

public interface MarkService{
    MarkDTO saveMark(MarkDTO markDTO);
    MarkDTO getMarkById(Long id);
    void deleteMark(Long id);
}