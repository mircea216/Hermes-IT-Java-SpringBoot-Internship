package ro.societateahermes.backendservice.entities.cdMember;

public enum DepartmentRole {
    PRESIDENT(1, "PREȘEDINTE"),
    VICE_PRESIDENT(2, "VICEPREȘEDINTE"),
    MANAGER(3, "MANAGER"),
    CENSOR(4, "CENZOR"),
    SECRETARY(5, "SECRETAR"),
    GENERAL_SECRETARY(6, "SECRETAR GENERAL"),
    AUXILIARY_IT_COORDINATOR(7, "COORDONATOR CORP AUXILIAR DE IT"),
    PROJECTS_VICE_PRESIDENT(8, "VICEPREȘEDINTE PROIECTE");

    private final int id;
    private final String name;

    DepartmentRole(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static DepartmentRole getRoleType(Integer id) {
        if (id == null) {
            return null;
        }

        for (DepartmentRole departmentRole : DepartmentRole.values()) {
            if (id.equals(departmentRole.getId())) {
                return departmentRole;
            }
        }

        throw new IllegalArgumentException("No matching department type for id " + id);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
