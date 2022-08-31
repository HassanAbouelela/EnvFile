package net.ashald.envfile.products.pycharm;

import com.intellij.execution.ExecutionException;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.jetbrains.python.run.AbstractPythonRunConfiguration;
import com.jetbrains.python.run.PythonExecution;
import com.jetbrains.python.run.PythonRunParams;
import com.jetbrains.python.run.target.HelpersAwareTargetEnvironmentRequest;
import com.jetbrains.python.run.target.PythonCommandLineTargetEnvironmentProvider;
import net.ashald.envfile.platform.ui.EnvFileConfigurationEditor;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class PythonProvider implements PythonCommandLineTargetEnvironmentProvider {
    private final Logger LOG = Logger.getInstance("envfile");
    @Override
    public void extendTargetEnvironment(@NotNull Project project, @NotNull HelpersAwareTargetEnvironmentRequest helpersAwareTargetEnvironmentRequest, @NotNull PythonExecution pythonExecution, @NotNull PythonRunParams pythonRunParams) {
        try {
            EnvFileConfigurationEditor.collectEnv(
                (AbstractPythonRunConfiguration<?>) pythonRunParams,
                new HashMap<>(pythonRunParams.getEnvs())
            ).forEach(pythonExecution::addEnvironmentVariable);
        } catch (ExecutionException e) {
            LOG.error(e);
        }
    }
}
