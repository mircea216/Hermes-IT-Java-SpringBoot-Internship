package ro.societateahermes.backendservice.entities.cdMember;

public enum Department {
    GENERAL(1, "GENERAL"),
    PR_IMAGE(2, "IMAGINE ȘI PR"),
    EXTERNAL_RELATIONS(3, "RELAȚII EXTERNE"),
    EVENTS(4, "EVENIMENTE"),
    HUMAN_RESOURCES(5, "RESURSE UMANE");

    private final int id;
    private final String name;

    Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Department getDepartmentType(Integer id) {
        if (id == null) {
            return null;
        }

        for (Department department : Department.values()) {
            if (id.equals(department.getId())) {
                return department;
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
