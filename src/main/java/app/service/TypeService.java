package app.service;

import app.dto.TypeCreationDto;
import app.exception.TypeAlreadyExists;
import app.items.TypeUnit;
import app.repository.TypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TypeService {
    private final TypeRepository repository;

    public TypeUnit createNewType(TypeCreationDto dto) throws TypeAlreadyExists {
        TypeUnit byName = repository.findByName(dto.getName());
        if (byName != null){
            throw new TypeAlreadyExists();
        }
        TypeUnit unit = new TypeUnit();
        unit.setDouble(dto.isDouble());
        unit.setName(dto.getName());
        unit.setShortName(dto.getShortName());
        return repository.save(unit);
    }

    public void deleteType(TypeUnit type){
        repository.findById(type.getId());
    }
}
