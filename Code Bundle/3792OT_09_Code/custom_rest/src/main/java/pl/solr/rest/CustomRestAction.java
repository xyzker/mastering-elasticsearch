package pl.solr.rest;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.admin.cluster.node.info.NodeInfo;
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.BytesRestResponse;
import org.elasticsearch.rest.RestChannel;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.RestRequest.Method;
import org.elasticsearch.rest.RestResponse;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.rest.action.support.RestBuilderListener;

public class CustomRestAction extends BaseRestHandler {
    @Inject
    public CustomRestAction(Settings settings,
            RestController controller, Client client) {
        super(settings, controller, client);
        controller.registerHandler(Method.GET, "/_mastering/nodes", this);
    }

    @Override
    public void handleRequest(RestRequest request, RestChannel channel, Client client) {
        final String prefix = request.param("prefix", "");
        client.admin().cluster().prepareNodesInfo().all().execute(
                new RestBuilderListener<NodesInfoResponse>(channel) {
                    @Override
                    public RestResponse buildResponse(
                            NodesInfoResponse response, XContentBuilder builder)
                            throws Exception {
                        List<String> nodes = new ArrayList<String>();
                        for (NodeInfo nodeInfo : response.getNodes()) {
                            String nodeName = nodeInfo.getNode().getName();
                            if (prefix.isEmpty()) {
                                nodes.add(nodeName);
                            } else if (nodeName.startsWith(prefix)) {
                                nodes.add(nodeName);
                            }
                        }
                        builder.startObject()
                            .field("nodes", nodes)
                        .endObject();
                        return new BytesRestResponse(RestStatus.OK, builder);
                    }});
    }

}
