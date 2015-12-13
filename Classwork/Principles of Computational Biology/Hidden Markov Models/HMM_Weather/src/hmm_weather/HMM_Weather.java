/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmm_weather;

/**
 *
 * @author thefabnab
 */
public class HMM_Weather
{
    
    private static String[] states = {"Rainy", "Sunny"};
  private static String[] observations = {"walk", "shop", "clean"};
  private static double[] start_probability = {0.6, 0.4};
  private static double[][] transition_probability = {{0.7, 0.3}, {0.4, 0.6}};
  private static double[][] emission_probability = {{0.1, 0.4, 0.5}, {0.6, 0.3, 0.1}};

  private static class TNode
  {
    public double prob;
    public int[] v_path;
    public double  v_prob;
    public TNode(double prob, int[] v_path, double v_prob)
    {
      this.prob = prob;
      this.v_path = copyIntArray(v_path);
      this.v_prob = v_prob;
    }
  }
  
  private static int[] copyIntArray(int[] ia){
    int[] newIa = new int[ia.length];
    for(int i=0; i<ia.length; i++){
      newIa[i] = ia[i];
    }
    return newIa;
  }
  
  private static int[] copyIntArray(int[] ia, int newInt){
    int[] newIa = new int[ia.length + 1];
    for(int i=0; i<ia.length; i++){
      newIa[i] = ia[i];
    }
    newIa[ia.length] = newInt;
    return newIa;
  }
  
  public static void forwardViterbi(String[] obs, String[] st, double[] sp, double[][] tp, double[][] ep) {
    TNode[] T = new TNode[st.length];
    for(int state=0; state<st.length; state++){
      int[] intArray = new int[1];
      intArray[0] = state;
      T[state] = new TNode(sp[state], intArray, sp[state]);
    }
    
    for(int output=0; output<obs.length; output++){
      TNode[] U = new TNode[st.length];
      for(int next_state=0; next_state<st.length; next_state++){
        double total = 0;
        int[] argmax = new int[0];
        double valmax = 0;
        for(int state=0; state<st.length; state++){
          double prob = T[state].prob;
          int[] v_path = copyIntArray(T[state].v_path);
          double v_prob = T[state].v_prob;
          double p = ep[state][output] * tp[state][next_state];
          prob *= p;
          v_prob *= p;
          total += prob;
          if(v_prob>valmax){
            argmax = copyIntArray(v_path, next_state);
            valmax = v_prob;
          }
        }
        U[next_state] = new TNode(total, argmax, valmax);
      }
      T = U;
    }
    // apply sum/max to the final states:
    double total = 0;
    int[] argmax = new int[0];
    double valmax = 0;
    for(int state=0; state<st.length; state++){
      double prob = T[state].prob;
      int[] v_path = copyIntArray(T[state].v_path);
      double v_prob = T[state].v_prob;
      total += prob;
      if(v_prob>valmax){
        argmax = copyIntArray(v_path);
        valmax = v_prob;
      }
    }
  }
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        System.out.println("Hey");
    }
    }
    

