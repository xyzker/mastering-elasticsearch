package pl.solr.rest;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.plugins.AbstractPlugin;
import org.elasticsearch.rest.RestModule;

public class CustomRestActionPlugin extends AbstractPlugin {

    @Inject
    public CustomRestActionPlugin(Settings settings) {
    }

    public void onModule(RestModule module) {
        module.addRestAction(CustomRestAction.class);
    }

    @Override
    public String name() {
        return "CustomRestActionPlugin";
    }

    @Override
    public String description() {
        return "Custom REST action";
    }

}
