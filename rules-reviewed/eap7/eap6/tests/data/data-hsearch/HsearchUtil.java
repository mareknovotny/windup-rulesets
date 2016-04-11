import org.hibernate.search.engine.impl.SearchMappingBuilder;
import org.apache.lucene.queryparser.flexible.core.builders.QueryBuilder;
import org.hibernate.search.Search;
import org.hibernate.SharedSessionBuilder;
import org.hibernate.search.Environment;
import org.hibernate.search.FullTextFilter;
import org.hibernate.search.indexes.impl.DirectoryBasedIndexManager;
import org.hibernate.search.infinispan.impl.InfinispanDirectoryProvider;
import org.hibernate.search.ProjectionConstants;
import org.hibernate.search.SearchException;
import org.hibernate.search.spi.MassIndexerFactory;
import org.hibernate.search.spi.SearchFactoryBuilder;
import org.hibernate.search.spi.SearchFactoryIntegrator;
import org.hibernate.search.Version;
import org.hibernate.search.annotations.FieldCacheType;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.cfg.NumericFieldMapping;
import org.hibernate.search.cfg.PropertyDescriptor;
import org.hibernate.search.cfg.EntityDescriptor;
import org.hibernate.search.cfg.SearchMapping;
import org.hibernate.search.cfg.ContainedInMapping;
import java.lang.annotation.ElementType;
import org.hibernate.search.store.Workspace;
import org.hibernate.search.filter.FilterKey;
import org.hibernate.search.filter.StandardFilterKey;
import org.hibernate.search.impl.FullTextSharedSessionBuilderDelegator;
import org.hibernate.search.backend.configuration.impl.IndexWriterSetting.MAX_THREAD_STATES;
import org.hibernate.search.FullTextSharedSessionBuilder;
import org.hibernate.search.engine.spi.SearchFactoryImplementor;

//import org.infinispan.manager.CacheManager;
//import org.infinispan.Cache;
//import org.hibernate.search.engine.service.spi.ServiceProvider;
import java.util.Properties;

import org.hibernate.search.cfg.IndexedMapping;
import org.hibernate.search.engine.spi.DocumentBuilderIndexedEntity;
import org.hibernate.search.query.engine.spi.HSQuery;
import org.hibernate.search.spi.BuildContext;
import org.hibernate.search.store.spi.DirectoryHelper;
import org.hibernate.search.MassIndexer;
import org.hibernate.search.query.dsl.FuzzyContext;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParserToken;
import org.apache.lucene.queryParser.QueryParserTokenMgrError;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.QueryParserToken;
import org.apache.lucene.queryParser.QueryParserTokenMgrError;

public class HsearchUtil {
    
    static FullTextSharedSessionBuilderDelegator(SharedSessionBuilder builder)
    
    public void main(String[] args) {
        
        FullTextSession fullTextSession = Search.getFullTextSession( s );
        SearchFactoryImplementor searchFactory = (SearchFactoryImplementor) fullTextSession.getSearchFactory();
        
        final QueryBuilder b = fullTextSession.getSearchFactory()
                    .buildQueryBuilder().forEntity( Book.class ).get();
        
        org.apache.lucene.search.Query luceneQuery =
                    b.keyword()
                        .onField("isbn").boostedTo(3)
                        .matching("12456797")
                        .createQuery();
        
        org.apache.lucene.search.Query luceneQuery1 =
                    b.
                    keyword()
                    .fuzzy()
                    .withThreshold(0.7f)
                        .onField("isbn").boostedTo(3)
                        .matching("12456797")
                        .createQuery();
        
        FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery( luceneQuery );;
        fulltextQuery.setSort(new Sort(new SortField("title", SortField.STRING)));
        List result = fullTextQuery.list();
        
        DirectoryHelper.getVerifiedIndexDir("indexname",new Properties(), true);

        fullTextSession
        .createIndexer( User.class )
        .batchSizeToLoadObjects( 25 )
        .cacheMode( CacheMode.NORMAL )
        .threadsToLoadObjects( 5 )
        .threadsForSubsequentFetching( 20 )
        .startAndWait();
        
        HSQuery query = queryContext.getFactory().createHSQuery();
        ExtendedSearchIntegrator searchIntegrator = query.getExtendedSearchIntegrator();
        // hsearch-00218
        searchIntegrator.getIndexingStrategy();
        
    }
    
    private void callConstructors(){
        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_42);
        
        PropertyDescriptor property = new PropertyDescriptor("bean", ElementType.FIELD);
        EntityDescriptor entityDescriptor = new EntityDescriptor();
        entityDescriptor.setCacheInMemory(new HashMap());
        entityDescriptor.getCacheInMemory();
        NumericFieldMapping numMapping = new NumericFieldMapping("test", PropertyDescriptor property, entityDescriptor, new SearchMapping()));
        
        SearchMapping searchmapping =  new SearchMapping();
        IndexMapping indexMapping = new IndexMapping(searchmapping, entityDescriptor);
        indexMapping.cacheFromIndex(FieldCacheType.CLASS);
        
        ContainedInMapping containedMapping = new ContainedInMapping(PropertyDescriptor property, new EntityDescriptor(),searchMapping));
        numMapping = containedMapping.numericField();
        
        FullTextSharedSessionBuilder builder = new FullTextSharedSessionBuilderDelegator();
        builder.autoClose();
    }
    
    public QueryParser getQuery() throws  ParseException {
        
        QueryParser parser = new QueryParser(Version.LUCENE_43, "title", analyzer);
        
        String querystr = "test*";
        Query query = parser.parse(querystr);
    }
    
    private String objectIdInString(Class<?> entityClass, Serializable id, ConversionContext conversionContext) { 
        EntityIndexBinder indexBindingForEntity = searchFactory.getIndexBindingForEntity( entityClass ); 
        if ( indexBindingForEntity == null ) { 
         throw new SearchException( "Unable to find entity type metadata while deserializing: " + entityClass ); 
        } 
        DocumentBuilderIndexedEntity<?> documentBuilder = indexBindingForEntity.getDocumentBuilder(); 
        return documentBuilder.objectToString( documentBuilder.getIdKeywordName(), id, conversionContext ); 
       } 
}
