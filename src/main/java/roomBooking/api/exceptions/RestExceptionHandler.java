package roomBooking.api.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RequiredNotNullValue.class})
    protected ResponseEntity<Object> handleRequiredNotNullValue(RuntimeException e, WebRequest request) {
        ExceptionBody body = ExceptionBody
                .builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .path(request.getDescription(true))
                .timestamp(new Date().toString())
                .build();
        return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }

    @ExceptionHandler(value = {ClientNotFoundExceptions.class})
    protected ResponseEntity<Object> handleClientNotFoundException(RuntimeException e, WebRequest request) {
        ExceptionBody body = ExceptionBody
                .builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getDescription(true))
                .timestamp(new Date().toString())
                .build();
        return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {BookingNotFoundException.class})
    protected ResponseEntity<Object> handleBookingNotFoundException(RuntimeException e, WebRequest request) {
        ExceptionBody body = ExceptionBody
                .builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getDescription(true))
                .timestamp(new Date().toString())
                .build();
        return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {RoomIsNotAvailable.class})
    protected ResponseEntity<Object> handleRoomIsNotAvailable(RuntimeException e, WebRequest request) {
        ExceptionBody body = ExceptionBody
                .builder()
                .message(e.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .path(request.getDescription(true))
                .timestamp(new Date().toString())
                .build();
        return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {WrongBookingDate.class})
    protected ResponseEntity<Object> handleWrongBookingDate(RuntimeException e, WebRequest request) {
        ExceptionBody body = ExceptionBody
                .builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .path(request.getDescription(true))
                .timestamp(new Date().toString())
                .build();
        return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }

    @ExceptionHandler(value = {RoomNotFoundException.class})
    protected ResponseEntity<Object> handleRoomNotFoundException(RuntimeException e, WebRequest request) {
        ExceptionBody body = ExceptionBody
                .builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getDescription(true))
                .timestamp(new Date().toString())
                .build();
        return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {WrongParameterException.class})
    protected ResponseEntity<Object> handleWrongParameterException(RuntimeException e, WebRequest request) {
        ExceptionBody body = ExceptionBody
                .builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getDescription(true))
                .timestamp(new Date().toString())
                .build();
        return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {PropertyNotFoundException.class})
    protected ResponseEntity<Object> handlePropertyNotFoundException(RuntimeException e, WebRequest request) {
        ExceptionBody body = ExceptionBody
                .builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getDescription(true))
                .timestamp(new Date().toString())
                .build();
        return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {WrongDataFormatException.class})
    protected ResponseEntity<Object> handleWrongDataFormatException(RuntimeException e, WebRequest request) {
        ExceptionBody body = ExceptionBody
                .builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .path(request.getDescription(true))
                .timestamp(new Date().toString())
                .build();
        return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }

    @ExceptionHandler(value = {CommentNotFoundException.class})
    protected ResponseEntity<Object> handleCommentNotFoundException(RuntimeException e, WebRequest request) {
        ExceptionBody body = ExceptionBody
                .builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getDescription(true))
                .timestamp(new Date().toString())
                .build();
        return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {HostNotFoundExceptions.class})
    protected ResponseEntity<Object> handleHostNotFoundExceptions(RuntimeException e, WebRequest request) {
        ExceptionBody body = ExceptionBody
                .builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getDescription(true))
                .timestamp(new Date().toString())
                .build();
        return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {MaxNumberOfRoomsException.class})
    protected ResponseEntity<Object> handleMaxNumberOfRoomsException(RuntimeException e, WebRequest request) {
        ExceptionBody body = ExceptionBody
                .builder()
                .message(e.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .path(request.getDescription(true))
                .timestamp(new Date().toString())
                .build();
        return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {RoomNotInPropertyException.class})
    protected ResponseEntity<Object> handleRoomNotInPropertyException(RuntimeException e, WebRequest request) {
        ExceptionBody body = ExceptionBody
                .builder()
                .message(e.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .path(request.getDescription(true))
                .timestamp(new Date().toString())
                .build();
        return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
