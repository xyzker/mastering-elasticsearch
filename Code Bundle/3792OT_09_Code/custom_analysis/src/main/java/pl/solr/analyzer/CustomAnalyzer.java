package pl.solr.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;

import java.io.Reader;

public class CustomAnalyzer extends Analyzer {
  public CustomAnalyzer() {
  }

  @Override
  protected TokenStreamComponents createComponents(String field, Reader reader) {
    final Tokenizer src = new WhitespaceTokenizer(reader);
    return new TokenStreamComponents(src, new CustomFilter(src));
  }
}
