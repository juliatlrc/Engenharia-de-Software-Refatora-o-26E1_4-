package pipeline;

/**
 * CÓDIGO ORIGINAL – NÃO MODIFICADO
 *
 * Este é o código legado do BuildPipeline-Refactoring-Kata.
 * Apresenta: método longo, múltiplas responsabilidades, condicionais aninhadas,
 * parâmetros primitivos expostos, baixa coesão e nenhum teste.
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

    public void run(Project project) {
        boolean testsPassed;
        boolean deploySuccessful;

        if (config.sendEmailSummary()) {
            log.info("Starting pipeline for " + project.name());

            if (!project.hasTests()) {
                log.info("No tests");
                testsPassed = true;
            } else {
                if (project.runTests().equals("success")) {
                    log.info("Tests passed");
                    testsPassed = true;
                } else {
                    log.error("Tests failed");
                    testsPassed = false;
                }
            }

            if (testsPassed) {
                if (project.deploy().equals("success")) {
                    log.info("Deployment successful");
                    deploySuccessful = true;
                } else {
                    log.error("Deployment failed");
                    deploySuccessful = false;
                }
            } else {
                deploySuccessful = false;
            }

            if (testsPassed) {
                if (deploySuccessful) {
                    emailer.send("Deployment completed successfully");
                } else {
                    emailer.send("Deployment failed");
                }
            } else {
                emailer.send("Tests failed");
            }
        } else {
            log.info("Starting pipeline for " + project.name());

            if (!project.hasTests()) {
                log.info("No tests");
                testsPassed = true;
            } else {
                if (project.runTests().equals("success")) {
                    log.info("Tests passed");
                    testsPassed = true;
                } else {
                    log.error("Tests failed");
                    testsPassed = false;
                }
            }

            if (testsPassed) {
                if (project.deploy().equals("success")) {
                    log.info("Deployment successful");
                    deploySuccessful = true;
                } else {
                    log.error("Deployment failed");
                    deploySuccessful = false;
                }
            } else {
                deploySuccessful = false;
            }
        }
    }
}
