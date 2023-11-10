package oauth2client.sociallogin.common.converters;

public interface ProviderUserConverter<T, R> {
    R converter(T t);
}
