package ru.nfbgu.converters;

import ru.nfbgu.dao.model.StudentModel;
import org.springframework.stereotype.Service;
import ru.nfbgu.service.dto.StudentDto;

@Service
public class StudentConverter implements Converter<StudentDto, StudentModel> {
    @Override
    public StudentModel to(StudentDto source) {
        return new StudentModel(
                null,
                source.getName(),
                source.getEmail(),
                null,
                source.getGroup()
        );
    }

    @Override
    public StudentDto from(StudentModel source) {
        return new StudentDto(
                source.getName(),
                source.getEmail(),
                source.getGroup()
        );
    }
}
