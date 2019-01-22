package pl.solr.analyzer;

import org.elasticsearch.common.inject.AbstractModule;

public class CustomAnalyzerModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(CustomAnalyzerIndicesComponent.class).asEagerSingleton();
  }
}
