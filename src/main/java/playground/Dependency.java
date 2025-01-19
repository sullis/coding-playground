package playground;

import java.util.ArrayList;
import java.util.List;

public class Dependency {
  public String name;
  public String version;
  public List<Dependency> dependencies = new ArrayList<>();
}
