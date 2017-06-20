package com.rebekahperkins.website.web.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.rebekahperkins.website.domain.Poem;
import com.rebekahperkins.website.service.PoemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@RunWith(MockitoJUnitRunner.class)
public class PoemControllerTest {

  private MockMvc mockMvc;

  private PoemController controller;

  @Mock
  private PoemService service;

  @Before
  public void setup() {
    controller = new PoemController();
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("classpath:/templates/");
    viewResolver.setSuffix(".html");
    mockMvc = MockMvcBuilders.standaloneSetup(controller)
        .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
        .setViewResolvers(viewResolver).build();
  }

  @Test
  public void cats_ShouldRenderCatsView() throws Exception {
    Page<Poem> page = new PageImpl<Poem>(list, new PageRequest(0,6), list.size());

   when(service.findAll(any(PageRequest.class))).thenReturn(page);


    mockMvc.perform(get("/cats").param("pageable", new Pag))

        .andExpect(view().name("cats"));
  }
}
