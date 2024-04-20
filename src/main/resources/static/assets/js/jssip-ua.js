/**
 * @author Jefferson Alves Reis (jefaokpta) < jefaokpta@hotmail.com >
 * Date: 27/10/23
 */
const socket = new JsSIP.WebSocketInterface(`wss://${webphone.domain}:8089/ws`)
const configuration = {
    sockets  : [ socket ],
    uri      : `sip:${webphone.peer}@` + webphone.domain,
    password : webphone.password,
    register : false,
};

const ua = new JsSIP.UA(configuration);
let session = null;
const phoneBtn = document.getElementById('phoneBtn');

uaEventHandling();
ua.start();

const callEventHandlers = {
    'progress': function(e) {
        console.log('Call em andamento');
    },
    'failed': function(e) {
        console.log(`Call falhou originator: ${e.originator} cause: ${e.cause}`);
    },
    'ended': function(e) {
        console.log(`Call terminou originator: ${e.originator} with cause: ${e.cause}`);
        changeCallBtnText('Testar Ligação')
        callBtnDoCall();
    },
    'confirmed': function(e) {
        console.log('Call atendida');
        changeCallBtnText('Desligar')
        callBtnDoHangup();
    }
};

function audioListener(session){
    session.connection.addEventListener('addstream', e => {
        console.log('remote audio stream added');
        const audio = new Audio()
        audio.srcObject = e.stream;
        audio.play();
    });
}

const callOptions = {
    'eventHandlers'    : callEventHandlers,
    'mediaConstraints' : { 'audio': true, 'video': false },
    'pcConfig'         : {
        'rtcpMuxPolicy': 'require',
        'iceServers'   : [
            { 'urls': 'stun:stun.l.google.com:19302' }
        ]
    }
};

function uaEventHandling() {
    //events of UA class with their callbacks

    ua.on('registered', function (e) {
        console.trace("REGISTRADO 🎉", e);
    });

    ua.on('unregistered', e => {
        console.trace("ua has been unregistered periodic registeration fails or ua.unregister()", e);
    });
    ua.on('registrationFailed', e => {
        console.trace("register failed", e);
    });
    ua.on('connected', e => {
        console.trace("conectado ao websocket do servidor de sinalização");
        enableCallBtn();
        callBtnDoCall();
        changeCallBtnText('Testar Ligação')
    });

    ua.on('disconnected', e => {
        console.trace("disconnected");
        ua.stop();
    });
    ua.on('newRTCSession', e => {
        session = e.session;
        if (e.originator === 'local') {
            console.trace(e.request + ' iniciou sessao de saida');
            audioListener(session);
        }
        else {
            console.trace(e.request + ' start incoming session');
            newSession(e.session);
        }
    });

}

function newSession(session){
    this.session = session;
    this.session.on('connecting', e => console.log('connecting'));
    this.session.on('peerconnection', e => {
        console.log('peerconnection')
        audioListener(session);
    });
    this.session.on('ended', e => console.log('ended'));
    this.session.on('failed', e => console.log('failed'));
    this.session.on('accepted', e => console.log('accepted'));
    this.session.on('confirmed', e => console.log(' confirmed'));
    this.session.on('addstream', e => console.log('addstream'));
}

function dial() {
    session = ua.call(`sip:123456@${webphone.domain}`, callOptions);
}

function hangup() {
    session.terminate();
}

function register() {
    ua.register();
}

function answer() {
    session.answer();
}

function enableCallBtn() {
    phoneBtn.classList.remove('disabled');
}

function changeCallBtnText(text) {
    phoneBtn.innerText = text;
}

function callBtnDoCall(){
    phoneBtn.removeEventListener('click', hangup);
    phoneBtn.addEventListener('click', dial);
}

function callBtnDoHangup(){
    phoneBtn.removeEventListener('click', dial);
    phoneBtn.addEventListener('click', hangup);
}

