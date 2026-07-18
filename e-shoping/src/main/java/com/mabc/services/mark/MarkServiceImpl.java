package com.mabc.services.mark;

import com.mabc.entities.Mark;
import com.mabc.repositories.MarkRepository;
import com.mabc.dto.MarkDTO;

import org.springframework.stereotype.Service;


@Service
public class MarkServiceImpl implements MarkService {
    private final MarkRepository markRepository;

    public MarkServiceImpl(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    @Override
    public Mark registerMark(Mark mark) {
        return markRepository.save(mark);
    }

    @Override
    public Mark getMarkById(Long id) {
        return markRepository.findById(id).orElse(null);
    }

    @Override
    public Mark updateMark(Long id, Mark mark) {
        if (markRepository.existsById(id)) {
            mark.setId(id);
            return markRepository.save(mark);
        }
        return null;
    }

    @Override
    public void deleteMark(Long id) {
        markRepository.deleteById(id);
    }
}