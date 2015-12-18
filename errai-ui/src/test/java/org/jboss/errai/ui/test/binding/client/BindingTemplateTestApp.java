package org.jboss.errai.ui.test.binding.client;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.jboss.errai.ioc.client.api.EntryPoint;
import org.jboss.errai.ui.client.widget.ListWidget;
import org.jboss.errai.ui.client.widget.Table;
import org.jboss.errai.ui.client.widget.UnOrderedList;
import org.jboss.errai.ui.shared.TemplateUtil;
import org.jboss.errai.ui.test.binding.client.res.BindingItemWidget;
import org.jboss.errai.ui.test.binding.client.res.BindingTemplate;
import org.jboss.errai.ui.test.binding.client.res.CompositeBindingTemplate;
import org.jboss.errai.ui.test.binding.client.res.NonCompositeBindingItem;
import org.jboss.errai.ui.test.binding.client.res.NonCompositeBindingTemplate;
import org.jboss.errai.ui.test.common.client.TestModel;

import com.google.gwt.user.client.ui.RootPanel;

@EntryPoint
public class BindingTemplateTestApp {

  @Inject
  private RootPanel root;

  @Inject
  private CompositeBindingTemplate compositeTemplate;

  @Inject
  private NonCompositeBindingTemplate nonCompositeTemplate;

  @Inject
  @UnOrderedList
  private ListWidget<TestModel, BindingItemWidget> ulListWidget;

  @Inject
  @Table
  private ListWidget<TestModel, BindingItemWidget> tableListWidget;

  @PostConstruct
  public void setup() {
    root.add(compositeTemplate);
    root.getElement().appendChild(TemplateUtil.asElement(nonCompositeTemplate.getRoot()));
  }

  public BindingTemplate<BindingItemWidget> getCompositeTemplate() {
    return compositeTemplate;
  }

  public BindingTemplate<NonCompositeBindingItem> getNonCompositeTemplate() {
    return nonCompositeTemplate;
  }

  public ListWidget<TestModel, BindingItemWidget> getUlListWidget() {
    return ulListWidget;
  }

  public ListWidget<TestModel, BindingItemWidget> getTableListWidget() {
    return tableListWidget;
  }
}
