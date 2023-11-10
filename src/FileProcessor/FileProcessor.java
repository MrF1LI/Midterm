package FileProcessor;

import FileProcessor.extensions.FileCopyException;
import FileProcessor.extensions.FileDeletionException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileProcessor implements FileSystemImpl {
    private static final Logger logger = Logger.getLogger(FileProcessor.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("file_operations.log");
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void copyFile(String sourceFileName, String destinationFileName) throws FileCopyException {
        try (FileInputStream inputStream = new FileInputStream(new File(sourceFileName));
             FileOutputStream outputStream = new FileOutputStream(new File(destinationFileName))) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            logger.log(Level.INFO, "File copied: " + sourceFileName + " to " + destinationFileName);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "File copy failed: " + e.getMessage());
            throw new FileCopyException("File copy failed: " + e.getMessage());
        }
    }

    @Override
    public void deleteFile(String fileName) throws FileDeletionException {
        File file = new File(fileName);

        if (file.exists()) {
            if (file.delete()) {
                logger.log(Level.INFO, "File '" + fileName + "' deleted successfully.");
            } else {
                String errorMessage = "Unable to delete file '" + fileName + "'";
                logger.log(Level.WARNING, errorMessage);
                throw new FileDeletionException("");
            }
        } else {
            String errorMessage = "File '" + fileName + "' not found.";
            logger.log(Level.WARNING, errorMessage);
            throw new FileDeletionException(errorMessage);
        }
    }
}
