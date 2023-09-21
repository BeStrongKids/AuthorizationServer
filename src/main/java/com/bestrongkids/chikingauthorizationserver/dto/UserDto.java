package com.bestrongkids.chikingauthorizationserver.dto;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
public record UserDto(String name, String email, String password) {

    public UserDto {
        checkArgument(isNotEmpty(name), "name must be provided");
        checkArgument(
                !name.isEmpty() && name.length() <= 10,
                "name length must be between 1 and 10 characters"
        );
        checkNotNull(email, "email must be provided");
        checkNotNull(password, "password must be provided");
    }
}
