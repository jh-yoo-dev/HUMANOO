package com.example.bookstore.dto;

import lombok.*;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import com.example.bookstore.model.entitiy.Member;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
    private Long id;
    
    @NotBlank(message = "required field")
    private String username;

    @NotBlank(message = "required field")
    @Email(message = "Please text email")
    private String email;

    @NotBlank(message = "required field")
    @Pattern(regexp ="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
    message="password must be English + number + mixture of special characters and 8 characters to 20 characters")
    private String password;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .email(email)
                .password(password)
                .build();
    }

    @Builder
    public MemberDto(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}