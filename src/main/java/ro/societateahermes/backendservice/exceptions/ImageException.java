package ro.societateahermes.backendservice.exceptions;

public class ImageException extends Exception {
    ImageException(Exception exception) {
        super(exception);
    }


    public ImageException(String message) {
        super(message);
    }
}
