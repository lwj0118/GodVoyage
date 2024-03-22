package org.godvoyage.godvoyage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private String email;
    private String password;
    private String name;
    private String address;
    private String telNum;

}
