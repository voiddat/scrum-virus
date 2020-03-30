package com.madsoft.scrumvirus.query.datamodel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Users")
public class UserDTO {
    @Id
    private Long id;
    private String username;
}
