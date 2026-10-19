package dansplugins.recipesystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MoreRecipesTest {
    /** Not the default endpoint, so a notice still naming the default is caught. */
    private static final String CONFIGURED_ENDPOINT = "http://trace.example.test:8080";

    @Test
    void usageReportingOnNotice_namesTheConfiguredEndpoint() {
        String notice = MoreRecipes.usageReportingOnNotice("More-Recipes", CONFIGURED_ENDPOINT);

        assertTrue(notice.contains("command names to " + CONFIGURED_ENDPOINT + ","), notice);
        assertFalse(notice.contains("trace.danielstephenson.dev"), notice);
    }

    @Test
    void usageReportingOnNotice_namesThePluginAndHowToTurnItOff() {
        String notice = MoreRecipes.usageReportingOnNotice("More-Recipes", CONFIGURED_ENDPOINT);

        assertTrue(notice.startsWith("Usage reporting is on: More-Recipes sends"), notice);
        assertTrue(notice.contains("usage-reporting.enabled: false"), notice);
        assertTrue(notice.contains("enabled: false in plugins/trace/config.yml"), notice);
    }
}
