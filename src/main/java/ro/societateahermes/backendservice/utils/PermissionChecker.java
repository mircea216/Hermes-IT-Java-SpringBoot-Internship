package ro.societateahermes.backendservice.utils;


import java.util.List;

public class PermissionChecker {


    public static final String EVENT_A = "2";
    public static final String EVENT_B = "1";
    public static final String EVENT_C = "4";
    public static final String EVENT_D = "3";


    public static boolean check(long eventId, List<String> roles) {
        switch (String.valueOf(eventId)) {
            case EVENT_A:
                return roles.contains("ADMIN") || roles.contains("PROJECT_MANAGER_DESTRESIUNE");
            case EVENT_B:
                return roles.contains("ADMIN") || roles.contains("PROJECT_MANAGER_CARIEREIT");
            case EVENT_C:
                return roles.contains("ADMIN") || roles.contains("PROJECT_MANAGER_GUIDEDAYS");
            case EVENT_D:
                return roles.contains("ADMIN") || roles.contains("PROJECT_MANAGER_HACKATHON");
            default:
                return false;
        }
    }

    public static boolean checkAdmin(List<String> roles) {
        return (roles.contains("ADMIN"));

    }

}
