package br.com.workmade.utils;

import br.com.workmade.api.exceptionHandler.Problem;
import br.com.workmade.api.exceptionHandler.ProblemType;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;

@Slf4j
public class Utils {

    @Autowired
    static Environment environment;

    public static String currentHostAndPort() {
        String url = "";
        // Port
        //environment.getProperty("server.port");
        try {

            String getHostName = InetAddress.getLocalHost().getHostName();
            String getHostAddress = InetAddress.getLocalHost().getHostAddress();
            log.info(getHostName);
            log.info(getHostAddress);


            String getRemoteHostAddress = InetAddress.getLoopbackAddress().getHostAddress();
            String getRemoteHostName = InetAddress.getLoopbackAddress().getHostName();
            log.info(getRemoteHostName);
            log.info(getRemoteHostAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            log.debug("currentHostAndPort", ExceptionUtils.unwrapInvocationTargetException(e));
        }

        return url;

    }

    public static HttpHeaders getHttpHeaders(WebRequest wr) {
        HttpHeaders headers = new HttpHeaders();
        for (Iterator<String> it = wr.getHeaderNames(); it.hasNext(); ) {
            String headerName = it.next();
            headers.add(headerName, wr.getHeader(headerName));
        }
        return headers;
    }

    public static Problem.ProblemBuilder createProblemBuilder(ProblemType type, HttpStatus status, String detail) {
        return Problem.builder()
                .status(status.value())
                .type(type.getUri())
                .title(type.getTitle())
                .detail(detail);
    }
}
