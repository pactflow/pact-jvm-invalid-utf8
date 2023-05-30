package pact.jvm.invalid.utf8;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "InvalidUTF8Provider", pactVersion = PactSpecVersion.V3)
public class PactTest {
  @Pact(consumer = "InvalidUTF8Provider")
  public RequestResponsePact pact(PactDslWithProvider builder) {
    return builder
      .uponReceiving("some request")
      .path("/")
      .method("GET")
      .willRespondWith()
      .status(200)
      .body(
        new PactDslJsonBody()
          .object("values")
            .stringMatcher("value1", ".*")
            .stringMatcher("value2", ".*")
            .stringMatcher("value3", ".*")
          .closeObject()
      )
      .toPact();
  }

  @Test
  void testPact(MockServer mockServer) throws IOException {
    ClassicHttpResponse httpResponse = (ClassicHttpResponse) Request.get(mockServer.getUrl()).execute().returnResponse();
    Assertions.assertEquals(httpResponse.getCode(), 200);
  }
}
