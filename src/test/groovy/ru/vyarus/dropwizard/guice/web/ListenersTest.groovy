package ru.vyarus.dropwizard.guice.web

import io.dropwizard.Application
import io.dropwizard.Configuration
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import ru.vyarus.dropwizard.guice.AbstractTest
import ru.vyarus.dropwizard.guice.GuiceBundle
import ru.vyarus.dropwizard.guice.module.GuiceyConfigurationInfo
import ru.vyarus.dropwizard.guice.module.installer.WebInstallersBundle
import ru.vyarus.dropwizard.guice.module.installer.feature.web.listener.WebListenerInstaller
import ru.vyarus.dropwizard.guice.support.web.listeners.ContextListener
import ru.vyarus.dropwizard.guice.test.spock.UseGuiceyApp

import javax.inject.Inject

/**
 * @author Vyacheslav Rusakov
 * @since 09.08.2016
 */
@UseGuiceyApp(ListApp)
class ListenersTest extends AbstractTest {

    @Inject
    GuiceyConfigurationInfo info

    def "Check listeners recognition"() {

        expect: "listeners recognized"
        info.getExtensions(WebListenerInstaller).size() == 8
    }

    static class ListApp extends Application<Configuration> {
        @Override
        void initialize(Bootstrap<Configuration> bootstrap) {
            bootstrap.addBundle(GuiceBundle.builder()
                    .enableAutoConfig(ContextListener.package.name)
                    .bundles(new WebInstallersBundle())
                    .build())
        }

        @Override
        void run(Configuration configuration, Environment environment) throws Exception {

        }
    }
}
