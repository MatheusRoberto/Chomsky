/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chomsky;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author matheus
 */
public class Chms {

    private Map<Character, Character> auxiliares = new HashMap<>();
    private Map<Character, ArrayList<String>> auxiliaresp = new HashMap<>();
    private Map<Character, ArrayList<String>> programa;
    private ArrayList<Character> terminais;
    private ArrayList<Character> variaveis;
    private char var = '1';

    public void transTam2() {
        for (Map.Entry<Character, ArrayList<String>> entry : getPrograma().entrySet()) {
            ArrayList<String> value = entry.getValue();
            for (String string : value) {
                if (string.length() == 2) {
                    value.remove(string);
                    String c0 = String.valueOf(string.charAt(0));
                    String c1 = String.valueOf(string.charAt(1));
                    for (Character terminai : terminais) {
                        String termin = String.valueOf(terminai);
                        //Troca terminal esquerda
                        if (c0.equals(termin)) {
                            if (!(auxiliares.containsKey(c0.charAt(0)))) {
                                if (terminalVariavel(c0.charAt(0))) {
                                    auxiliares.put(c0.charAt(0), Character.toUpperCase(c0.charAt(0)));
                                    c0 = String.valueOf(Character.toUpperCase(c0.charAt(0)));
                                } else {
                                    auxiliares.put(c0.charAt(0), var);
                                    c0 = String.valueOf(var);
                                    var++;
                                }
                            } else {
                                char c = auxiliares.get(c0.charAt(0));
                                c0 = String.valueOf(c);
                            }
                        }
                        if (c1.equals(termin)) {
                            if (!(auxiliares.containsKey(c1.charAt(0)))) {
                                if (terminalVariavel(c1.charAt(0))) {
                                    auxiliares.put(c1.charAt(0), Character.toUpperCase(c1.charAt(0)));
                                    c1 = String.valueOf(Character.toUpperCase(c1.charAt(0)));
                                } else {
                                    auxiliares.put(c1.charAt(0), var);
                                    c1 = String.valueOf(var);
                                    var++;
                                }
                            } else {
                                char c = auxiliares.get(c1.charAt(0));
                                c1 = String.valueOf(c);
                            }
                        }
                    }
                    String nova = String.valueOf(c0);
                    nova = nova + String.valueOf(c1);
                    value.add(nova);
                }
            }
        }

        for (Map.Entry<Character, Character> entry
                : auxiliares.entrySet()) {
            Character key = entry.getKey();
            Character value = entry.getValue();
            ArrayList<String> corpo = new ArrayList<>();
            corpo.add(String.valueOf(key));
            programa.put(value, corpo);
        }
    }

    public void transTam3() {
        for (Map.Entry<Character, ArrayList<String>> entry : getPrograma().entrySet()) {
            ArrayList<String> value = entry.getValue();
            for (String string : value) {
                if (string.length() == 3) {
                    value.remove(string);
                    String c0 = String.valueOf(string.charAt(0));
                    String c1 = String.valueOf(string.charAt(1));
                    String c2 = String.valueOf(string.charAt(1));
                    for (Character termi : terminais) {
                        String termin = String.valueOf(termi);
                        //Troca terminal esquerda
                        if (c0.equals(termin)) {
                            if (!(auxiliares.containsKey(c0.charAt(0)))) {
                                if (terminalVariavel(c0.charAt(0))) {
                                    auxiliares.put(c0.charAt(0), Character.toUpperCase(c0.charAt(0)));
                                    c0 = String.valueOf(Character.toUpperCase(c0.charAt(0)));
                                } else {
                                    auxiliares.put(c0.charAt(0), var);
                                    c0 = String.valueOf(var);
                                    var++;
                                }
                            } else {
                                char c = auxiliares.get(c0.charAt(0));
                                c0 = String.valueOf(c);
                            }
                        }
                        if (c1.equals(termin)) {
                            if (!(auxiliares.containsKey(c1.charAt(0)))) {
                                if (terminalVariavel(c1.charAt(0))) {
                                    auxiliares.put(c1.charAt(0), Character.toUpperCase(c1.charAt(0)));
                                    c1 = String.valueOf(Character.toUpperCase(c1.charAt(0)));
                                } else {
                                    auxiliares.put(c1.charAt(0), var);
                                    c1 = String.valueOf(var);
                                    var++;
                                }
                            } else {
                                char c = auxiliares.get(c1.charAt(0));
                                c1 = String.valueOf(c);
                            }
                        }
                        if (c2.equals(termin)) {
                            if (!(auxiliares.containsKey(c2.charAt(0)))) {
                                if (terminalVariavel(c2.charAt(0))) {
                                    auxiliares.put(c2.charAt(0), Character.toUpperCase(c2.charAt(0)));
                                    c2 = String.valueOf(Character.toUpperCase(c2.charAt(0)));
                                } else {
                                    auxiliares.put(c2.charAt(0), var);
                                    c2 = String.valueOf(var);
                                    var++;
                                }
                            } else {
                                char c = auxiliares.get(c2.charAt(0));
                                c2 = String.valueOf(c);
                            }
                        }
                    }
                    ArrayList<String> corpoaux = new ArrayList<>();
                    String nova = String.valueOf(c0);
                    nova = nova + String.valueOf(c1);
                    corpoaux.add(nova);
                    auxiliaresp.put(var, corpoaux);
                    nova = String.valueOf(var);
                    nova = nova + String.valueOf(c2);
                    value.add(nova);
                }
            }
        }

        for (Map.Entry<Character, Character> entry
                : auxiliares.entrySet()) {
            Character key = entry.getKey();
            Character value = entry.getValue();
            ArrayList<String> corpo = new ArrayList<>();
            corpo.add(String.valueOf(key));
            programa.put(value, corpo);
        }

        for (Map.Entry<Character, ArrayList<String>> entry
                : auxiliaresp.entrySet()) {
            Character key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            programa.put(key, value);
        }
    }

    public boolean terminalVariavel(char a) {
        return !programa.entrySet().stream().map((entry) -> entry.getKey()).noneMatch((key) -> (key == a));
    }

    /**
     * @return the auxiliares
     */
    public Map<Character, Character> getAuxiliares() {
        return auxiliares;
    }

    /**
     * @param auxiliares the auxiliares to set
     */
    public void setAuxiliares(Map<Character, Character> auxiliares) {
        this.auxiliares = auxiliares;
    }

    /**
     * @return the programa
     */
    public Map<Character, ArrayList<String>> getPrograma() {
        return programa;
    }

    /**
     * @param programa the programa to set
     */
    public void setPrograma(Map<Character, ArrayList<String>> programa) {
        this.programa = programa;
    }

    /**
     * @return the terminais
     */
    public ArrayList<Character> getTerminais() {
        return terminais;
    }

    /**
     * @param terminais the terminais to set
     */
    public void setTerminais(ArrayList<Character> terminais) {
        this.terminais = terminais;
    }

    /**
     * @return the variaveis
     */
    public ArrayList<Character> getVariaveis() {
        return variaveis;
    }

    /**
     * @param variaveis the variaveis to set
     */
    public void setVariaveis(ArrayList<Character> variaveis) {
        this.variaveis = variaveis;
    }

}
