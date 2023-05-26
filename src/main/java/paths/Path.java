package paths;

import java.io.File;

public class Path {
    public static final String PATH_TO_RESOURCES;
    public static final String PATH_TO_FIGHT_RESOURCES;
    public static final String PATH_TO_LEFT_ACTOR_RESOURCES;
    public static final String PATH_TO_RIGHT_ACTOR_RESOURCES;

    static {
        PATH_TO_RESOURCES = String.format("%s\\src\\main\\resources\\", new File("").getAbsolutePath());
        PATH_TO_FIGHT_RESOURCES = PATH_TO_RESOURCES + "fight\\";
        PATH_TO_LEFT_ACTOR_RESOURCES = PATH_TO_FIGHT_RESOURCES + "leftActor\\";
        PATH_TO_RIGHT_ACTOR_RESOURCES = PATH_TO_FIGHT_RESOURCES + "rightActor\\";
    }
}
