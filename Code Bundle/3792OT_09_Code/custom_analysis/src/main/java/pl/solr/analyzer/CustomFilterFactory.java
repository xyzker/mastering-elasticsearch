package pl.solr.analyzer;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;
import org.elasticsearch.index.settings.IndexSettings;

public class CustomFilterFactory extends AbstractTokenFilterFactory {
  @Inject
  public CustomFilterFactory(Index index, @IndexSettings Settings indexSettings, @Assisted String name, @Assisted Settings settings) {
    super(index, indexSettings, name, settings);
  }

  @Override
  public TokenStream create(TokenStream tokenStream) {
    return new CustomFilter(tokenStream);
  }
}
