package ru.vyarus.dropwizard.guice.module.installer;

/**
 * Installer serve two purposes: find extension on classpath and properly install it
 * (in dropwizard or somewhere else). Each installer should work with single feature.
 * <p>Installers are not guice beans: they are instantiated during guice context start and used to register
 * additional beans in guice context.</p>
 * Installer may choose from three possible types of installation:
 * <ul>
 * <li>{@link ru.vyarus.dropwizard.guice.module.installer.install.binding.BindingInstaller} to apply specific
 * guice bindings (called in process of injector creation, whereas other installer are called after)</li>
 * <li>{@link ru.vyarus.dropwizard.guice.module.installer.install.TypeInstaller} to register class
 * in environment or somewhere else</li>
 * <li>{@link ru.vyarus.dropwizard.guice.module.installer.install.InstanceInstaller} to register instance
 * in environment or somewhere else</li>
 * <li>{@link ru.vyarus.dropwizard.guice.module.installer.install.JerseyInstaller} to register jersey related
 * extension</li>
 * </ul>
 * Even if installer doesn't implement any of these types, extension will be still registered in guice.
 *
 * @param <T> expected extension type (or Object when no super type (e.g. for annotated beans))
 */
public interface FeatureInstaller<T> {

    /**
     * NOTE: consider using {@code ru.vyarus.dropwizard.guice.module.installer.util.FeatureUtils} to simplify checks
     * (for example, types most likely must be checks to be not abstract).
     * <p>When type accepted by any extension it's registered in guice module.</p>
     *
     * @param type type to check
     * @return true if extension recognized, false otherwise
     */
    boolean matches(Class<?> type);

    /**
     * Called to log registered endpoints in dropwizard log fashion.
     * It's important to naturally show all dynamically resolved classes to easily catch errors.
     * Use {@link ru.vyarus.dropwizard.guice.module.installer.util.Reporter} to simplify reporting.
     * <p>Method may do nothing if reporting not required</p>
     */
    void report();
}
