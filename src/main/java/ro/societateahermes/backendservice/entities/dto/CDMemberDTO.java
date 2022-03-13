package ro.societateahermes.backendservice.entities.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.societateahermes.backendservice.entities.cdMember.Department;
import ro.societateahermes.backendservice.entities.cdMember.DepartmentRole;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CDMemberDTO {
    private Long id;
    @NotEmpty(message = "Image path cannot be null!")
    private String imagePath;
    @NotEmpty(message = "Facebook link cannot be null!")
    private String facebookLink;
    @NotEmpty(message = "Description is mandatory!")
    private String description;
    @NotEmpty(message = "Name is mandatory!")
    private String name;
    private Integer departmentId;
    @NotNull(message = "Role is mandatory!")
    private Integer roleId;

    public String getDepartmentName() {
        Department departmentType = Department.getDepartmentType(departmentId);
        return departmentType == null ? null : departmentType.getName();
    }

    public String getRoleName() {
        return DepartmentRole.getRoleType(roleId).getName();
    }

    @JsonIgnore
    public Department getDepartmentType() {
        return Department.getDepartmentType(departmentId);
    }

    @JsonIgnore
    public DepartmentRole getRoleType() {
        return DepartmentRole.getRoleType(roleId);
    }
}
