package FileProcessor;

import FileProcessor.extensions.FileCopyException;
import FileProcessor.extensions.FileDeletionException;

public interface FileSystemImpl {
    void copyFile (String sourceFileName, String destinationFileName) throws FileCopyException;
    void deleteFile(String fileName) throws FileDeletionException;
}
