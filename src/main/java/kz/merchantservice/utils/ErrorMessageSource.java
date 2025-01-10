package kz.merchantservice.utils;

public enum ErrorMessageSource {

    CITY_ALREADY_EXISTS("City already exists: %s"),
    CITY_NOT_FOUND("City not found: %s"),
    ;

    private String text;

    ErrorMessageSource(String text) {
        this.text = text;
    }

    public String getText(String... params) {
        return String.format(this.text, (Object[]) params);
    }
}
