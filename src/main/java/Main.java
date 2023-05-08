import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {
        Path path = Path.of(Objects.requireNonNull(Main.class
                .getClassLoader().getResource("maps/map_1.txt")).toURI());
        Map map = Map.generateMapFromFile(path);
        map.proceed();
        System.out.println(map.toFile());
    }
}