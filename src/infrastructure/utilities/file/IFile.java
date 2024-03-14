package src.infrastructure.utilities.file;

/**
 * Interface for writing, updating, and deleting objects in a file.
 * Implementing classes are responsible for handling the specific details of file operations.
 */
public interface IFile {

    /**
     * Path to the file
     */
    String FILE_PATH_ROOT = "src/infrastructure/persistence/data/";
}
