package pipeline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * PipelineTest – Exercícios 1 e 4
 *
 * Testes escritos ANTES da refatoração (safety net) e novos testes
 * adicionados para cobrir comportamentos anteriormente não testados.
 *
 * Usa doubles manuais (sem frameworks de mock) para manter legibilidade.
 */
public class PipelineTest {

    // ── Doubles manuais ──────────────────────────────────────────────────────

    private static class CapturingEmailer implements Emailer {
        final List<String> sentMessages = new ArrayList<>();
        @Override public void send(String message) { sentMessages.add(message); }
        boolean nothingSent() { return sentMessages.isEmpty(); }
        String lastMessage() { return sentMessages.get(sentMessages.size() - 1); }
    }

    private static class CapturingLogger implements Logger {
        final List<String> infos  = new ArrayList<>();
        final List<String> errors = new ArrayList<>();
        @Override public void info(String msg)  { infos.add(msg); }
        @Override public void error(String msg) { errors.add(msg); }
        boolean hasError(String fragment) {
            return errors.stream().anyMatch(e -> e.contains(fragment));
        }
        boolean hasInfo(String fragment) {
            return infos.stream().anyMatch(i -> i.contains(fragment));
        }
    }

    private static class StubConfig implements Config {
        private final boolean send;
        StubConfig(boolean send) { this.send = send; }
        @Override public boolean sendEmailSummary() { return send; }
    }

    private static class StubProject implements Project {
        private final boolean hasTests;
        private final String testResult;
        private final String deployResult;

        StubProject(boolean hasTests, String testResult, String deployResult) {
            this.hasTests     = hasTests;
            this.testResult   = testResult;
            this.deployResult = deployResult;
        }
        @Override public String name()       { return "my-project"; }
        @Override public boolean hasTests()  { return hasTests; }
        @Override public String runTests()   { return testResult; }
        @Override public String deploy()     { return deployResult; }
    }

    // ── Fixtures ─────────────────────────────────────────────────────────────

    private CapturingEmailer emailer;
    private CapturingLogger  logger;

    @BeforeEach
    void setUp() {
        emailer = new CapturingEmailer();
        logger  = new CapturingLogger();
    }

    private Pipeline pipeline(boolean sendEmail) {
        return new Pipeline(new StubConfig(sendEmail), emailer, logger);
    }

    // ── Testes: projeto sem testes ────────────────────────────────────────────

    @Test
    void projectWithoutTests_deploysSuccessfully_andSendsEmail() {
        StubProject project = new StubProject(false, null, "success");
        pipeline(true).run(project);

        assertTrue(logger.hasInfo("No tests"));
        assertTrue(logger.hasInfo("Deployment successful"));
        assertEquals("Deployment completed successfully", emailer.lastMessage());
    }

    @Test
    void projectWithoutTests_deployFails_sendsFailureEmail() {
        StubProject project = new StubProject(false, null, "failed");
        pipeline(true).run(project);

        assertTrue(logger.hasInfo("No tests"));
        assertTrue(logger.hasError("Deployment failed"));
        assertEquals("Deployment failed", emailer.lastMessage());
    }

    // ── Testes: projeto com testes ────────────────────────────────────────────

    @Test
    void testsPass_deploySucceeds_sendsSuccessEmail() {
        StubProject project = new StubProject(true, "success", "success");
        pipeline(true).run(project);

        assertTrue(logger.hasInfo("Tests passed"));
        assertTrue(logger.hasInfo("Deployment successful"));
        assertEquals("Deployment completed successfully", emailer.lastMessage());
    }

    @Test
    void testsFail_deploySkipped_sendsTestsFailedEmail() {
        StubProject project = new StubProject(true, "failed", "success");
        pipeline(true).run(project);

        assertTrue(logger.hasError("Tests failed"));
        assertEquals("Tests failed", emailer.lastMessage());
    }

    @Test
    void testsPass_deployFails_sendsDeploymentFailedEmail() {
        StubProject project = new StubProject(true, "success", "failed");
        pipeline(true).run(project);

        assertTrue(logger.hasInfo("Tests passed"));
        assertTrue(logger.hasError("Deployment failed"));
        assertEquals("Deployment failed", emailer.lastMessage());
    }

    // ── Testes: sem e-mail ────────────────────────────────────────────────────

    @Test
    void whenEmailDisabled_noEmailSentEvenOnSuccess() {
        StubProject project = new StubProject(true, "success", "success");
        pipeline(false).run(project);

        assertTrue(emailer.nothingSent(), "Nenhum e-mail deve ser enviado");
        assertTrue(logger.hasInfo("Deployment successful"));
    }

    @Test
    void whenEmailDisabled_testsFail_noEmailSent() {
        StubProject project = new StubProject(true, "failed", "success");
        pipeline(false).run(project);

        assertTrue(emailer.nothingSent(), "Nenhum e-mail deve ser enviado");
        assertTrue(logger.hasError("Tests failed"));
    }

    // ── Testes: log inicial ────────────────────────────────────────────────────

    @Test
    void alwaysLogsProjectName() {
        StubProject project = new StubProject(false, null, "success");
        pipeline(false).run(project);

        assertTrue(logger.hasInfo("my-project"));
    }
}
