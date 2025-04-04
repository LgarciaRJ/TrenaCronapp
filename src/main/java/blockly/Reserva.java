package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;
import org.springframework.web.bind.annotation.*;


@CronapiMetaData(type = "blockly")
@CronappSecurity
public class Reserva {

public static final int TIMEOUT = 300;

/**
 *
 * @param Reserva<app.entity.Reserva>
 *
 * @author Iago Sousa
 * @since 3/28/2025, 10:52:12 AM
 *
 */
public static void antesDeInserir(@ParamMetaData(description = "Reserva", id = "dac277af") @RequestBody(required = false) Var Reserva) throws Exception {
  new Callable<Var>() {

   private Var vagaAtual = Var.VAR_NULL;
   private Var periodoAtivo = Var.VAR_NULL;

   public Var call() throws Exception {
    vagaAtual =
    cronapi.database.Operations.query(Var.valueOf("app.entity.Vaga"),Var.valueOf("select \n	v \nfrom \n	Vaga v  \nwhere \n	v.id = :id"),Var.valueOf("id",
    cronapi.object.Operations.getObjectField(
    cronapi.object.Operations.getObjectField(Reserva,
    Var.valueOf("vaga")),
    Var.valueOf("id"))));
    if (
    Var.valueOf(
    cronapi.database.Operations.getField(vagaAtual,
    Var.valueOf("this[0].status")).equals(
    Var.valueOf("Fechada"))).getObjectAsBoolean()) {
        cronapi.util.Operations.throwException(
        Var.valueOf("Essa vaga já está ocupada!"));
    }
    periodoAtivo =
    cronapi.database.Operations.query(Var.valueOf("app.entity.Periodo"),Var.valueOf("select \n	p \nfrom \n	Periodo p  \nwhere \n	p.ativo = true"));
    if (
    cronapi.database.Operations.hasElement(periodoAtivo).getObjectAsBoolean()) {
        cronapi.database.Operations.updateField(Reserva,
        Var.valueOf("periodo"),
        cronapi.database.Operations.getField(periodoAtivo, Var.valueOf("this[0].periodo")));
    } else {
        cronapi.util.Operations.throwException(
        Var.valueOf("Não há um período ativo, contate o administrador do sistema!"));
    }
    if (
    cronapi.list.Operations.getFirst((
    cronapi.database.Operations.query(Var.valueOf("app.entity.Reserva"),Var.valueOf("select \n	r.ativo \nfrom \n	Reserva r  \nwhere \n	r.user.normalizedUserName = :userNormalizedUserName"),Var.valueOf("userNormalizedUserName",
    cronapi.text.Operations.normalize(
    cronapi.util.Operations.getCurrentUserName()))))).getObjectAsBoolean()) {
        cronapi.util.Operations.throwException(
        Var.valueOf("Você só pode reservar uma vaga por vez."));
    } else {
        cronapi.database.Operations.updateField(Reserva,
        Var.valueOf("ativo"),
        Var.VAR_TRUE);
    }
   return Var.VAR_NULL;
   }
 }.call();
}

/**
 *
 * @param Reserva<app.entity.Reserva>
 *
 * @author Iago Sousa
 * @since 3/28/2025, 10:52:12 AM
 *
 */
public static void depoisDeDeletar(@ParamMetaData(description = "Reserva", id = "f82e5636") @RequestBody(required = false) Var Reserva) throws Exception {
  new Callable<Var>() {

   private Var objetoVagaReservada = Var.VAR_NULL;

   public Var call() throws Exception {
    objetoVagaReservada =
    cronapi.database.Operations.query(Var.valueOf("app.entity.Vaga"),Var.valueOf("select \n	v \nfrom \n	Vaga v  \nwhere \n	v.id = :id"),Var.valueOf("id",
    cronapi.object.Operations.getObjectField(
    cronapi.object.Operations.getObjectField(Reserva,
    Var.valueOf("vaga")),
    Var.valueOf("id"))));
    cronapi.database.Operations.updateField(objetoVagaReservada,
    Var.valueOf("status"),
    Var.valueOf("Aberta"));
   return Var.VAR_NULL;
   }
 }.call();
}

/**
 *
 * @param Reserva<app.entity.Reserva>
 *
 * @author Iago Sousa
 * @since 3/28/2025, 10:52:12 AM
 *
 */
public static void depoisDeInserir(@ParamMetaData(description = "Reserva", id = "f82e5636") @RequestBody(required = false) Var Reserva) throws Exception {
  new Callable<Var>() {

   private Var objetoVagaReservada = Var.VAR_NULL;

   public Var call() throws Exception {
    objetoVagaReservada =
    cronapi.database.Operations.query(Var.valueOf("app.entity.Vaga"),Var.valueOf("select \n	v \nfrom \n	Vaga v  \nwhere \n	v.id = :id"),Var.valueOf("id",
    cronapi.object.Operations.getObjectField(
    cronapi.object.Operations.getObjectField(Reserva,
    Var.valueOf("vaga")),
    Var.valueOf("id"))));
    cronapi.database.Operations.updateField(objetoVagaReservada,
    Var.valueOf("status"),
    Var.valueOf("Fechada"));
   return Var.VAR_NULL;
   }
 }.call();
}

}

