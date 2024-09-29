package tech.ada.jjh.homebroker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import tech.ada.jjh.homebroker.dto.FeeDTO;
import tech.ada.jjh.homebroker.model.Fee;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeeMapper {

    Fee toEntity(FeeDTO feeDTO);
    FeeDTO toDTO(Fee fee);
    List<FeeDTO> listToDto(List<Fee> fees);
}
