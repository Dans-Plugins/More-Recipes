package dansplugins.recipesystem.trace;

import java.util.function.Function;

/**
 * Lets tests outside this package reach {@link TraceClient#environment}, so a
 * {@code DO_NOT_TRACK} on the machine running the suite cannot disable a client
 * they expect to be enabled.
 */
public final class TraceClientTestEnvironment {
    private TraceClientTestEnvironment() {
    }

    /**
     * Makes clients built from now on see no environment variables.
     * @return The environment in place before, to hand to {@link #restore}.
     */
    public static Function<String, String> clear() {
        Function<String, String> previous = TraceClient.environment;
        TraceClient.environment = name -> null;
        return previous;
    }

    /**
     * @param environment The environment {@link #clear} returned.
     */
    public static void restore(Function<String, String> environment) {
        TraceClient.environment = environment;
    }
}
