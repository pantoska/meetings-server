package com.ztp.mettings.user;

import com.ztp.mettings.error.common.ResourceNotFoundProblem;
import com.ztp.mettings.user.dto.UpdateUserRequestDto;
import com.ztp.mettings.user.dto.UserInfoDto;
import com.ztp.mettings.user.dto.UserPersonalDataDto;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    UserInfoDto getUserInfo(String id) {
        var user = findByIdOrThrow404(id);
        return new UserInfoDto(
                user.getEmail(),
                user.isEmailVerified(),
                user.getRoles().stream().map(Enum::toString).collect(Collectors.toSet()));
    }

    UserPersonalDataDto getPersonalData(String id) {
        var user = findByIdOrThrow404(id);
        return user.toUserPersonalDataDto();
    }

    UserPersonalDataDto updateCurrentUser(String id, UpdateUserRequestDto requestDto) {
        var user = findByIdOrThrow404(id);

        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());


        userRepository.save(user);
        return user.toUserPersonalDataDto();
    }

    private UserEntity findByIdOrThrow404(String id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundProblem("User", "id", id));
    }

}
