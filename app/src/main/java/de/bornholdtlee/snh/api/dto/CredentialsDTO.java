package de.bornholdtlee.snh.api.dto;


import lombok.Getter;

@Getter
public class CredentialsDTO {

    private String email;
    private String password;

    public CredentialsDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
