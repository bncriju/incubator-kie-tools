package org.jboss.errai.ui.test.handler.client;

import org.jboss.errai.enterprise.client.cdi.AbstractErraiCDITest;
import org.jboss.errai.ioc.client.container.IOC;
import org.junit.Test;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class HandlerTemplateTest extends AbstractErraiCDITest {

  @Override
  public String getModuleName() {
    return getClass().getName().replaceAll("client.*$", "Test");
  }

  @Test
  public void testInsertAndReplaceWithCompositeTemplate() {
    final HandlerTemplateTestApp app = IOC.getBeanManager().lookupBean(CompositeHandlerTemplateTestApp.class).getInstance();
    runAssertions(app);
    IOC.getBeanManager().destroyBean(app);
  }

  @Test
  public void testInsertAndReplaceWithNonCompositeTemplate() {
    final HandlerTemplateTestApp app = IOC.getBeanManager().lookupBean(NonCompositeHandlerTemplateTestApp.class).getInstance();
    runAssertions(app);
    IOC.getBeanManager().destroyBean(app);
  }

  private void runAssertions(final HandlerTemplateTestApp app) {
    assertNotNull("Component was null", app.getComponent());

    assertNotNull("b1 is not in the DOM.", Document.get().getElementById("b1"));
    assertEquals("b1 on page is not from component.", Document.get().getElementById("b1"), app.getComponent().getB1().getElement());
    assertTrue("b1 is not logically attached.", app.getComponent().getB1().isAttached());
    app.getComponent().getB1().click();
    assertFalse("b1 is still logically attached.", app.getComponent().getB1().isAttached());
    assertNull("b1 was not removed from the DOM.", Document.get().getElementById("b1"));

    assertNotNull("b2 is not in the DOM.", Document.get().getElementById("b2"));
    app.getComponent().getB2().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        app.getComponent().getB2().removeFromParent();
      }
    });

    assertTrue("b2 is not logically attached.", app.getComponent().getB2().isAttached());
    app.getComponent().getB2().click();

    assertFalse("b2 is still logically attached.", app.getComponent().getB2().isAttached());
    assertNull("b2 was not removed from the DOM.", Document.get().getElementById("b2"));
    assertNotNull("b3 was removed from the DOM.", Document.get().getElementById("b3"));
  }
}