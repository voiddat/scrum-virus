package com.madsoft.scrumvirus.query.datamodel;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Users")
@Builder
public class UserDTO {
    @Id
    private final Long id;
    private final String username;
}
