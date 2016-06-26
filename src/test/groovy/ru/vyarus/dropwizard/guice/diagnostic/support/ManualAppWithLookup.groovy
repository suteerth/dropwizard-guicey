package ru.vyarus.dropwizard.guice.diagnostic.support

import io.dropwizard.Application
import io.dropwizard.Configuration
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import ru.vyarus.dropwizard.guice.GuiceBundle
import ru.vyarus.dropwizard.guice.diagnostic.support.features.FooInstaller
import ru.vyarus.dropwizard.guice.diagnostic.support.features.FooModule
import ru.vyarus.dropwizard.guice.diagnostic.support.features.FooResource
import ru.vyarus.dropwizard.guice.module.installer.feature.jersey.ResourceInstaller

/**
 * @author Vyacheslav Rusakov
 * @since 26.06.2016
 */
class ManualAppWithLookup extends Application<Configuration> {

    @Override
    void initialize(Bootstrap<Configuration> bootstrap) {
        bootstrap.addBundle(GuiceBundle.builder()
                // test bundles from abstract test lookup is enabled by default
                .installers(ResourceInstaller, FooInstaller)
                .extensions(FooResource)
                .modules(new FooModule())
                .disableInstallers(FooInstaller)
                .build())
    }

    @Override
    void run(Configuration configuration, Environment environment) throws Exception {
    }
}
