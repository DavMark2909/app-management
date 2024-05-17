package app.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TypeCreationDto {
    private final String name;
    private final String shortName;
    private final boolean isDouble;
}
