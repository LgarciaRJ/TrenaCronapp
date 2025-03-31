window.blockly = window.blockly || {};
window.blockly.js = window.blockly.js || {};
window.blockly.js.blockly = window.blockly.js.blockly || {};
window.blockly.js.blockly.LogicaProgWeb = window.blockly.js.blockly.LogicaProgWeb || {};

/**
 * @function Executar
 *
 *
 *
 * @param entradaUsuario
 *
 * @author Iago Sousa
 * @since 3/25/2025, 11:59:51 AM
 *
 */
window.blockly.js.blockly.LogicaProgWeb.ExecutarArgs = [{ description: 'entradaUsuario', id: 'dcf87173' }];
window.blockly.js.blockly.LogicaProgWeb.Executar = async function(entradaUsuario) {
 var respostaServidor;
  //
  this.cronapi.util.callServerBlocklyAsynchronous('blockly.LogicaProg:Executar', async function(sender_respostaServidor) {
      respostaServidor = sender_respostaServidor;
    //
    this.cronapi.notification.customNotify('info', respostaServidor, 'fade', 'top', 'center', 'false');
  }.bind(this), entradaUsuario);
}
