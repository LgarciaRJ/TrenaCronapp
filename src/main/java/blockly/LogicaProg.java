package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;
import org.springframework.web.bind.annotation.*;


@CronapiMetaData(type = "blockly")
@CronappSecurity
public class LogicaProg {

public static final int TIMEOUT = 300;

/**
 *
 * @param valorEntrada
 *
 * @author Iago Sousa
 * @since 3/26/2025, 10:45:30 AM
 *
 */
public static Var Executar(@ParamMetaData(description = "valorEntrada", id = "f8cb613c") @RequestBody(required = false) Var valorEntrada) throws Exception {
 return new Callable<Var>() {

   public Var call() throws Exception {
    return
Var.valueOf(
Var.valueOf("A resposta é: ").getObjectAsString() +
Var.valueOf(validPalindrome(valorEntrada)).getObjectAsString());
   }
 }.call();
}

/**
 *
 * @param stringRomano
 *
 * @author Iago Sousa
 * @since 3/26/2025, 10:45:30 AM
 *
 */
public static Var romanoParaInteiro(@ParamMetaData(description = "stringRomano", id = "639f8c5d") @RequestBody(required = false) Var stringRomano) throws Exception {
 return new Callable<Var>() {

   private Var mapaRomano = Var.VAR_NULL;
   private Var resposta = Var.VAR_NULL;
   private Var numAnterior = Var.VAR_NULL;
   private Var i = Var.VAR_NULL;
   private Var numAtual = Var.VAR_NULL;
   private Var i_start = Var.VAR_NULL;
   private Var i_end = Var.VAR_NULL;
   private Var i_inc = Var.VAR_NULL;

   public Var call() throws Exception {
    mapaRomano =
    cronapi.map.Operations.createObjectMapWith(Var.valueOf("I",
    Var.valueOf(1)) , Var.valueOf("V",
    Var.valueOf(5)) , Var.valueOf("X",
    Var.valueOf(10)) , Var.valueOf("L",
    Var.valueOf(50)) , Var.valueOf("C",
    Var.valueOf(100)) , Var.valueOf("D",
    Var.valueOf(500)) , Var.valueOf("M",
    Var.valueOf(1000)));
    resposta =
    Var.valueOf(0);
    numAnterior =
    Var.valueOf(0);
    i_start =
    Var.valueOf(stringRomano.length());
    i_end =
    Var.valueOf(1);
    i_inc =
    Var.valueOf(1);
    if (i_start.greaterThan(i_end)) {
        i_inc.multiply(-1);
    }
    for (i = Var.valueOf(i_start);
        i_inc.getObjectAsInt() >= 0 ? i.getObjectAsLong() <= i_end.getObjectAsLong() : i.getObjectAsLong()  >= i_end.getObjectAsLong();
    i.inc(i_inc))  {
        numAtual =
        cronapi.map.Operations.getMapField(mapaRomano,
        Var.valueOf(cronapi.text.Operations.getLetter(stringRomano,i)));
        if (
        Var.valueOf(numAtual.compareTo(numAnterior) < 0).getObjectAsBoolean()) {
            resposta =
            cronapi.math.Operations.subtract(resposta,numAtual);
        } else {
            resposta =
            cronapi.math.Operations.sum(resposta,numAtual);
        }
        numAnterior = numAtual;
    } // end for
    return resposta;
   }
 }.call();
}

/**
 *
 * @param frase
 *
 * @author Iago Sousa
 * @since 3/26/2025, 10:45:30 AM
 *
 */
public static Var validPalindrome(@ParamMetaData(description = "param_frase", id = "ae4985b1") @RequestBody(required = false) Var param_frase) throws Exception {
 return new Callable<Var>() {

   // param
   private Var frase = param_frase;
   // end
   private Var fim = Var.VAR_NULL;
   private Var comeco = Var.VAR_NULL;
   private Var caractereComeco = Var.VAR_NULL;
   private Var caractereFim = Var.VAR_NULL;
   private Var letrasDigitos = Var.VAR_NULL;

   public Var call() throws Exception {
    // Essa função checa se uma frase é um palindromo
    fim =
    Var.valueOf(frase.length());
    comeco =
    Var.valueOf(1);
    while (
    Var.valueOf(comeco.compareTo(fim) <= 0).getObjectAsBoolean()) {
        caractereComeco =
        Var.valueOf(cronapi.text.Operations.getLetter(frase,comeco));
        caractereFim =
        Var.valueOf(cronapi.text.Operations.getLetter(frase,fim));
        System.out.println(caractereComeco.getObjectAsString());
        System.out.println(comeco.getObjectAsString());
        System.out.println(caractereFim.getObjectAsString());
        System.out.println(fim.getObjectAsString());
        // region Checar se é caractere ou digito
            letrasDigitos =
            Var.valueOf("abcdefghijklmnopqrstuvwxyz0123456789");
            if (
            Var.valueOf(
            Var.valueOf(letrasDigitos.getObjectAsString().indexOf(
            Var.valueOf(caractereComeco.getObjectAsString().toLowerCase()).getObjectAsString()) + 1).compareTo(
            Var.valueOf(0)) > 0).getObjectAsBoolean()) {
                comeco =
                cronapi.math.Operations.sum(comeco,
                Var.valueOf(1));
            } else if (
            Var.valueOf(
            Var.valueOf(letrasDigitos.getObjectAsString().indexOf(
            Var.valueOf(caractereFim.getObjectAsString().toLowerCase()).getObjectAsString()) + 1).compareTo(
            Var.valueOf(0)) > 0).getObjectAsBoolean()) {
                fim =
                cronapi.math.Operations.subtract(fim,
                Var.valueOf(1));
            } else {
                if (
                Var.valueOf(!caractereComeco.equals(caractereFim)).getObjectAsBoolean()){
                  return
                Var.VAR_FALSE;
                }
                comeco =
                cronapi.math.Operations.sum(comeco,
                Var.valueOf(1));
                fim =
                cronapi.math.Operations.subtract(fim,
                Var.valueOf(1));
            }
        // endregion Checar se é caractere ou digito
    } // end while
    return
Var.VAR_TRUE;
   }
 }.call();
}

}

