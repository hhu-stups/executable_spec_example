package de.prob2;

import com.google.inject.Guice;
import de.prob.Main;
import de.prob.animator.domainobjects.FormulaExpand;
import de.prob.scripting.Api;
import de.prob.scripting.ModelTranslationError;
import de.prob.statespace.State;
import de.prob.statespace.StateSpace;
import de.prob.statespace.Trace;

import java.io.IOException;
import java.util.stream.IntStream;

import static de.prob.Main.getInjector;
import static de.prob.animator.domainobjects.FormulaExpand.EXPAND;

public class RandomSteps {

  public static void main(String[] args) throws Exception {

    String filename = args[0];
    int steps = Integer.parseInt(args[1]);
    Api api = getInjector().getInstance(Api.class);
    StateSpace stateSpace = api.b_load(args[0]);
    Trace trace = new Trace(stateSpace);
    for (int i = 0; i < steps; i++) {
      trace = trace.anyOperation(null);
    }
    State state = trace.getCurrentState();
    state.getVariableValues(EXPAND).forEach((k, v) -> {
      System.out.println(k + "=>" + v);
    });
    stateSpace.kill();
  }


}
