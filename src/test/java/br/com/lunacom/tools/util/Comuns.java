package br.com.lunacom.tools.util;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.ws.rs.core.MediaType;

public class Comuns {
    public static MockHttpServletRequestBuilder getMockHttpServletRequestBuilder(String url, String json) {
        return MockMvcRequestBuilders
                .post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
    }
    public static MockHttpServletRequestBuilder getMockHttpServletPatchRequestBuilder(String url) {
        return MockMvcRequestBuilders
                .patch(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }
    public static MockHttpServletRequestBuilder getMockHttpServletGetRequestBuilder(String url) {
        return MockMvcRequestBuilders
                .get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }
}
