package pl.solr.analyzer;

import org.elasticsearch.index.analysis.AnalysisModule;

public class CustomAnalysisBinderProcessor extends AnalysisModule.AnalysisBinderProcessor {
  @Override
  public void processAnalyzers(AnalyzersBindings analyzersBindings) {
    analyzersBindings.processAnalyzer("mastering_analyzer", CustomAnalyzerProvider.class);
  }

  @Override
  public void processTokenFilters(TokenFiltersBindings tokenFiltersBindings) {
    tokenFiltersBindings.processTokenFilter("mastering_filter", CustomFilterFactory.class);
  }
}
