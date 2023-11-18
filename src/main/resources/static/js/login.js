$(document).ready(function() {

});

async function iniciarSesion(){
    let datos = {};
    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;

    const request = await fetch('api/login', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(datos)
    });
    //const respuesta = await request.json(); No es un json, es un texto, por eso lo siguiente:
    const respuesta = await request.text();

    if(respuesta != 'FAIL') { // Queda pendiente cambiar por el tipo de error, que sería el 401

        // Se guarda el tocke en el local storage
        localStorage.token = respuesta;
        localStorage.email = datos.email;

        window.location.href = 'usuarios.html';
    } else {
        alert('Las credenciales son invalidas.');
    }

}