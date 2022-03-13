package ro.societateahermes.backendservice.entities.cdMember;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CDMember {
    @Id
    @GeneratedValue
    private Long CDMemberID;
    private String imagePath;
    private String facebookLink;
    private String description;
    private String name;
    private Integer departmentId;
    private Integer roleId;

    public Department getDepartmentType() {
        return Department.getDepartmentType(departmentId);
    }

    public DepartmentRole getRoleType() {
        return DepartmentRole.getRoleType(roleId);
    }
}
