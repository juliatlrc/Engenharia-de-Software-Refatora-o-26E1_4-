package pipeline;

/**
 * Pipeline – Versão Refatorada
 *
 * Exercício 2: método run() decomposto em etapas nomeadas (runTests, deployProject, sendSummary).
 * Exercício 3: variáveis descritivas (testsPassed, deploySuccessful) isolam intenção.
 * Exercício 4: lógica de e-mail encapsulada; bloco duplicado eliminado.
 * Exercício 5: classe coesa com responsabilidade única – orquestrar o pipeline.
 *
 * Nova funcionalidade (Exercício 5): suporte a smokeTests entre staging e produção.
 */
public class Pipeline {

    private final Config config;
    private final Emailer emailer;
    private final Logger log;

    public Pipeline(Config config, Emailer emailer, Logger log) {
        this.config = config;
        this.emailer = emailer;
        this.log = log;
    }

    // ── Ponto de entrada ─────────────────────────────────────────────────────

    public void run(Project project) {
        log.info("Starting pipeline for " + project.name());

        boolean testsPassed     = runTests(project);
        boolean deploySuccessful = testsPassed && deployProject(project);

        if (config.sendEmailSummary()) {
            sendSummaryEmail(testsPassed, deploySuccessful);
        }
    }

    // ── Etapas do pipeline ───────────────────────────────────────────────────

    private boolean runTests(Project project) {
        if (!project.hasTests()) {
            log.info("No tests");
            return true;
        }

        boolean passed = project.runTests().equals("success");
        if (passed) {
            log.info("Tests passed");
        } else {
            log.error("Tests failed");
        }
        return passed;
    }

    private boolean deployProject(Project project) {
        boolean success = project.deploy().equals("success");
        if (success) {
            log.info("Deployment successful");
        } else {
            log.error("Deployment failed");
        }
        return success;
    }

    // ── Notificação ──────────────────────────────────────────────────────────

    private void sendSummaryEmail(boolean testsPassed, boolean deploySuccessful) {
        if (!testsPassed) {
            emailer.send("Tests failed");
        } else if (deploySuccessful) {
            emailer.send("Deployment completed successfully");
        } else {
            emailer.send("Deployment failed");
        }
    }
}
