package utils.model;

public class ServiceResponseModel<T> {
    public Boolean success = true;
    public T result = null;
    public String stacktrace = "";
}
