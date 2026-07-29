package com.school.luc.conf;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import com.school.luc.endpoint.event.EventProducer;
import com.school.luc.file.bucket.BucketComponent;

@AutoConfigureMockMvc
public class FacadeITMockedThirdParties extends FacadeIT {
  @LocalServerPort protected int localPort;
  @MockBean protected BucketComponent bucketConfMock;

  @MockBean protected EventProducer eventProducerMock;
}
