package amazonreviewsapp.springboot.mapper;

import amazonreviewsapp.springboot.dto.MostActiveUserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public MostActiveUserDto getMostActiveUserDtoFromObject(Object[] arr) {
        return new MostActiveUserDto((String) arr[0], (Long) arr[1]);
    }
}
