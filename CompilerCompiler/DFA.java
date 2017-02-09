import java.util.*;

/**
 *  DFA - provides implementation of a DFA
 *          (for purposes of lexical analysis)
 *
 *  @author S. Blythe
 *  @version 8/2016
 */
public class DFA
{
    private int nextStates[][];   // 2-D table that represents the DFA
    private int numStates;        // total number of states in DFA
    private int maxASCIICode;     // max ASCII val to represent (i.e. max chr)

    private Set<Integer> finalStates; // list of final states

    /**
     * DFA constructor for building "empty" DFA with specfied number of states
     *   @param numStates the number of states in the DFA
     *   @param maxASCIIcode the largest character to use as a transition
     */
    public DFA(int numStates, int maxASCIIcode)
    {
	// initialize instance variables
	this.numStates = numStates;
	this.maxASCIICode = maxASCIIcode;

	// buid 2-D array according to specs
	nextStates = new int[numStates][maxASCIICode+1];

	// set all deefault transitions to invalid (-1)
	for (int s=0; s<numStates; s++)
	    for(int c=0; c<=maxASCIICode; c++)
		nextStates[s][c]=-1;

	// create list for final states
	finalStates = new TreeSet<Integer>();
    }

    /**
     *setTransition inserts a transition ito the DFA
     * @param fromState where transition starts
     * @param toState where transition ends
     * @param OnSymbol the symbol upon which this transition should be followed
     */
    public void setTransition(int fromState, int toState, char OnSymbol)
    {
	// unpdate table accrdingly
	nextStates[fromState][(int) OnSymbol] = toState;
    }

    /**
     * nextState gives next State in dfa, based on current state and input
     * @param currState the current state yuo are transitioning from
     * @param OnSymbol the symbol to use to decide what state to go to next
     * @return the next state
     */
    public int nextState(int currState, char OnSymbol)
    {
	return nextStates[currState][(int) OnSymbol];
    }


    /**
     * markFinal sets a state as a final state
     * @param the state to mark as a final state.
     */
    public void markFinal(int currState)
    {
	finalStates.add(currState);
    }

    /**
     * isFinal describes whether a state is final in this DFA
     * @param state the state in question
     * @return whether or not the state is a fina state
     */
    public boolean isFinal(int state)
    {
	return false;
    }
}