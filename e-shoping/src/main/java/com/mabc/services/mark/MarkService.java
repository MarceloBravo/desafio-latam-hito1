package com.mabc.services.mark;

import com.mabc.entities.Mark;

public interface MarkService{
    Mark registerMark(Mark mark);
    Mark getMarkById(Long id);
    Mark updateMark(Long id, Mark mark);
    void deleteMark(Long id);
}