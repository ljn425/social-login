package oauth2client.sociallogin.converters;

public interface ProviderUserConverter<T, R> {
    R converter(T t);
}
