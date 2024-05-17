package app.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ItemCreationDto {
    private final String name;
    private final String type;
    private final double price;
    private final boolean isDouble;
}