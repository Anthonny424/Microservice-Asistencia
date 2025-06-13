package microservice.auth.dto;



public class AuthResponseDTO {
    private String token;
    private Long iduser;

    public AuthResponseDTO() {
    }

    public AuthResponseDTO(String token, Long iduser) {
        this.token = token;
        this.iduser = iduser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }
}
