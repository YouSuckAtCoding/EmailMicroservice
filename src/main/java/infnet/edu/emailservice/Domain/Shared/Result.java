package infnet.edu.emailservice.Domain.Shared;

public class Result<T> extends ResultRoot{

    private T _value;
    
    public Result(T value, boolean isSuccess, Error error) throws Exception {
        super(isSuccess, error);
        _value = value;
    }

    public T Value() throws Exception
    {
        if(this.IsSuccess)
            return _value;
        throw new Exception();
    }

    public static <T> Result<T> Success(T value) throws Exception
    {
        return new Result<T>(value, true, infnet.edu.emailservice.Domain.Shared.Error.None());   
    }
    public static <T> Result<T> Failure(Error error) throws Exception
    {
        return new Result<T>(null, false, error);   
    }
}
