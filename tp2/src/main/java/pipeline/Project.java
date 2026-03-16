package pipeline;

public interface Project {
    String name();
    boolean hasTests();
    String runTests();
    String deploy();
}
