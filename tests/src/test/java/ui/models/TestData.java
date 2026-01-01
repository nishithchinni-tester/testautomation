package ui.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestData {
    private String env;
    private String userName;
    private String password;
    private String email;
    private String appPassword;
    private String emailSubject;
    private String appName;
    private String tableName;
}