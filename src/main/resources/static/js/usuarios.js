// Call the dataTables jQuery plugin
$(document).ready(function() {
  cargarUsuarios();
  $('#usuarios').DataTable();
  actualizarEmailDelUsuario();
});

function actualizarEmailDelUsuario() {
    document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}

async function cargarUsuarios(){

    const request = await fetch('api/usuarios', {
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
      },
      //body: JSON.stringify({a: 1, b: 'Textual content'})
    });
    const usuarios = await request.json();

    console.log(usuarios);

    let listadoHtml = '';

    for(let usuario of usuarios){
        let btnEliminar = '<a href="#" onclick="eliminarUsuario(' + usuario.id + ')" class="btn btn-danger btn-circle btn-sm">' +
                          '<i class="fas fa-trash"></i></a>';

        let telefono = usuario.telefono == null ? '-' : usuario.telefono;

        let usuarioHTML = '<tr><td>' + usuario.id + '</td>' +
                      '<td>' + usuario.nombre + ' ' + usuario.apellido + '</td>' +
                      '<td>' + usuario.email + '</td>' +
                      '<td>' + telefono + '</td>' +
                      '<td>' + btnEliminar + '</td></tr>';
        listadoHtml += usuarioHTML;
    }

    document.querySelector('#usuarios tbody').outerHTML = listadoHtml;

}

async function eliminarUsuario(id) {
    //alert(id);

    if(!confirm('¿Desea eliminar este usuario?')) {
        return;
    }

    const request = await fetch('api/usuarios/' + id, {
          method: 'DELETE',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            //Se agrega el tocken en el header
            'Authorization': localStorage.token
          },
          //body: JSON.stringify({a: 1, b: 'Textual content'})
        });
    location.reload();
}

