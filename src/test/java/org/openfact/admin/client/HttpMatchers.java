package org.openfact.admin.client;

import org.hamcrest.CustomMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import javax.ws.rs.core.Response;

public class HttpMatchers {

    public static Matcher<Response> successful() {
        return new CustomMatcher<Response>("2xx family of successful responses") {
            @Override
            public boolean matches(Object o) {
                return (o instanceof Response)
                        && (((Response) o).getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL);
            }

            @Override
            public void describeMismatch(Object item, Description description) {
                Response response = (Response) item;
                provideDescription(response, description);
            }

        };
    }

    public static Matcher<Response> clientError() {
        return new CustomMatcher<Response>("4xx (client error) family of responses") {

            @Override
            public boolean matches(Object o) {
                return (o instanceof Response)
                        && (((Response) o).getStatusInfo().getFamily() == Response.Status.Family.CLIENT_ERROR);
            }

            @Override
            public void describeMismatch(Object item, Description description) {
                Response response = (Response) item;
                provideDescription(response, description);
            }

        };
    }

    public static Matcher<Response> redirection() {
        return new CustomMatcher<Response>("3xx (redirection) family of responses") {

            @Override
            public boolean matches(Object o) {
                return (o instanceof Response)
                        && (((Response) o).getStatusInfo().getFamily() == Response.Status.Family.REDIRECTION);
            }

            @Override
            public void describeMismatch(Object item, Description description) {
                Response response = (Response) item;
                provideDescription(response, description);
            }

        };
    }

    public static Matcher<Response> informational() {
        return new CustomMatcher<Response>("1xx (informational) family of responses") {

            @Override
            public boolean matches(Object o) {
                return (o instanceof Response)
                        && (((Response) o).getStatusInfo().getFamily() == Response.Status.Family.INFORMATIONAL);
            }

            @Override
            public void describeMismatch(Object item, Description description) {
                Response response = (Response) item;
                provideDescription(response, description);
            }
        };
    }

    public static Matcher<Response> other() {
        return new CustomMatcher<Response>("unrecognized family of responses") {

            @Override
            public boolean matches(Object o) {
                return (o instanceof Response)
                        && (((Response) o).getStatusInfo().getFamily() == Response.Status.Family.OTHER);
            }

            @Override
            public void describeMismatch(Object item, Description description) {
                Response response = (Response) item;
                provideDescription(response, description);
            }
        };
    }

    public static Matcher<Response> noContent() {
        final int statusCode = Response.Status.NO_CONTENT.getStatusCode();
        return new CustomMatcher<Response>("no content (" + statusCode + ")") {

            @Override
            public boolean matches(Object o) {
                return (o instanceof Response)
                        && (((Response) o).getStatus() == statusCode);
            }

            @Override
            public void describeMismatch(Object item, Description description) {
                Response response = (Response) item;
                provideDescription(response, description);
            }

        };
    }

    public static Matcher<Response> serverError() {
        final int statusCode = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        return new CustomMatcher<Response>("Internal server error(" + statusCode + ")") {

            @Override
            public boolean matches(Object o) {
                return (o instanceof Response)
                        && (((Response) o).getStatus() == statusCode);
            }

            @Override
            public void describeMismatch(Object item, Description description) {
                Response response = (Response) item;
                provideDescription(response, description);
            }

        };
    }

    public static Matcher<Response> created() {
        final int statusCode = Response.Status.CREATED.getStatusCode();
        return new CustomMatcher<Response>("created status (" + statusCode + ") with location header") {

            @Override
            public boolean matches(Object o) {
                if (!(o instanceof Response)) {
                    return false;
                }
                Response response = (Response) o;
                if (response.getStatus() == statusCode) {
                    String header = response.getHeaderString("Location");
                    return (header != null);
                } else {
                    return false;
                }
            }

            @Override
            public void describeMismatch(Object item, Description description) {
                Response response = (Response) item;
                provideDescription(response, description);
            }
        };
    }

    static void provideDescription(Response response, Description description) {
        description.appendText(response.getStatusInfo().getReasonPhrase() + " " + response.getStatus()).
                appendText(" returned");
    }

}
